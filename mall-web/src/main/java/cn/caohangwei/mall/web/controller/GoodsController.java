package cn.caohangwei.mall.web.controller;

import cn.caohangwei.mall.common.base.BaseController;
import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.common.util.JSONUtil;
import cn.caohangwei.mall.common.util.MD5Util;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.common.util.UUIDUtil;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.model.*;
import cn.caohangwei.mall.shop.rpc.api.ShopGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopOrderInfoService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeOrderService;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.web.base.GoodsDetail;
import cn.caohangwei.mall.shop.common.base.ShopGoodsPrefix;
import cn.caohangwei.mall.web.base.RabbitMQSender;
import cn.caohangwei.mall.web.base.SpikeMessages;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import static cn.caohangwei.mall.web.config.InitializingConfig.map;

@Api(value = "商品系统", tags = "GoodsController")
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private ShopSpikeGoodsService shopSpikeGoodsService;

    @Autowired
    private ShopOrderInfoService shopOrderInfoService;

    @Autowired
    private ShopSpikeOrderService shopSpikeOrderService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    RabbitMQSender rabbitMQSender;

    @RequestMapping(value = "/to_list", produces = "text/html", method = RequestMethod.GET)
    @ResponseBody
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String html = RedisUtil.get(ShopGoodsPrefix.GOODS_LIST, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        model.addAttribute("goodsList", shopGoodsService.getGoodsDetailList());
        IWebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", context);
        RedisUtil.set(ShopGoodsPrefix.GOODS_LIST, "", html);
        return html;
    }

    @RequestMapping("/detail/{goodsId}")
    @ResponseBody
    public BaseResult toDetail(UcenterUser user,
                               @PathVariable("goodsId") long goodsId) {
        ShopSpikeGoodsDetail shopSpikeGoodsDetail = shopGoodsService.getGoodsDetailByPrimaryKey(goodsId);
        long startAt = shopSpikeGoodsDetail.getSpikeStartTime().getTime();
        long endAt = shopSpikeGoodsDetail.getSpikeEndTime().getTime();
        long now = System.currentTimeMillis();
        int status = 0;
        int remainSeconds = 0;
        if (now < startAt) {
            status = 0;
            remainSeconds = (int) (startAt - now) / 1000;
        } else if (now > endAt) {
            status = 2;
            remainSeconds = -1;
        } else {
            status = 1;
            remainSeconds = 0;
        }
        GoodsDetail goodsDetail = new GoodsDetail();
        goodsDetail.setStatus(status);
        goodsDetail.setRemainSeconds(remainSeconds);
        goodsDetail.setShopSpikeGoodsDetail(shopSpikeGoodsDetail);
        goodsDetail.setUcenterUser(user);
        return new BaseResult(BaseResultEnum.SUCCESS, goodsDetail);
    }


    @RequestMapping(value = "/spike/{path}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult goodsSpike(UcenterUser user,
                                 @RequestParam("goodsId") Long goodsId,
                                 @PathVariable("path") String path) {
        if (null == user) {
            return new BaseResult(BaseResultEnum.SESSION_ERROR, null);
        }
        String str = RedisUtil.get(ShopGoodsPrefix.SPIKE_GOODS_PATH,user.getId()+"_"+goodsId,String.class);
        path = JSONUtil.beanToString(path);
        if(!str.equals(path)){
            return new BaseResult(BaseResultEnum.PATH_NOT_EXISTS,null);
        }
        //内存标记，减少redis访问
        if(map.get(goodsId)){
            return new BaseResult(BaseResultEnum.SPIKE_ERROR, null);
        }
        long stock = RedisUtil.decr(ShopGoodsPrefix.SPIKE_GOODS_STOCK, "" + goodsId);
        //预减库存
        if (stock <= 0) {
            map.put(goodsId,true);
            return new BaseResult(BaseResultEnum.SPIKE_ERROR, null);
        }
        //判断是否秒杀过
        ShopSpikeOrder shopSpikeOrder = RedisUtil.get(ShopGoodsPrefix.SPIKE_ORDER,"" + goodsId + user.getId(),ShopSpikeOrder.class);
        if(null != shopSpikeOrder){
            return new BaseResult(BaseResultEnum.PURCHASED_ERROR,null);
        }
        //入队
        SpikeMessages messages = new SpikeMessages();
        messages.setGoodsId(goodsId);
        messages.setUcenterUser(user);
        rabbitMQSender.send(messages);
        return new BaseResult(BaseResultEnum.SUCCESS, null);
    }

    @RequestMapping(value = "spike/result",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult spikeResult(UcenterUser user,@RequestParam("goodsId") Long goodsId){
        if(null == user){
            return new BaseResult(BaseResultEnum.SESSION_ERROR,null);
        }
        long result = shopSpikeOrderService.getSpikeResult(user.getId(),goodsId);
        return new BaseResult(BaseResultEnum.SUCCESS,result);
    }

    @RequestMapping(value = "/spike/path",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult getSpikeGoodsPath(UcenterUser user,
                                        @RequestParam("goodsId") Long goodsId,
                                        @RequestParam("verifyCode") Integer verifyCode){
        Integer code = RedisUtil.get(ShopGoodsPrefix.VERIFY_CODE,user.getId()+"_"+goodsId,Integer.class);
        if(code == null || code-verifyCode != 0){
            return new BaseResult(BaseResultEnum.VERIFY_ERROR,null);
        }
        RedisUtil.delete(ShopGoodsPrefix.VERIFY_CODE,user.getId()+"_"+goodsId);
        String str = MD5Util.md5(UUIDUtil.randomUUID() + "embassy");
        RedisUtil.set(ShopGoodsPrefix.SPIKE_GOODS_PATH,user.getId() + "_" + goodsId,str);
        return new BaseResult(BaseResultEnum.SUCCESS,str);
    }

    private static final char[] op = {'+','-','*'};

    @RequestMapping(value = "/spike/verifyCode",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult getVerifyCode(UcenterUser user,@RequestParam("goodsId") Long goodsId,HttpServletResponse response){
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = op[rdm.nextInt(3)];
        char op2 = op[rdm.nextInt(3)];
        String exp = "" + num1 + op1 + num2 + op2 + num3;
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(exp, 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = 0;
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
            rnd = (Integer) scriptEngine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RedisUtil.set(ShopGoodsPrefix.VERIFY_CODE, user.getId()+"_"+goodsId, rnd);
        try {
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return null;
        }catch(Exception e) {
            e.printStackTrace();
            return new BaseResult(BaseResultEnum.SPIKE_ERROR,null);
        }
    }
}
