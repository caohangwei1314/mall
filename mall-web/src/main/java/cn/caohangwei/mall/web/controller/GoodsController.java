package cn.caohangwei.mall.web.controller;

import cn.caohangwei.mall.common.base.BaseController;
import cn.caohangwei.mall.common.base.BasePrefix;
import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.model.*;
import cn.caohangwei.mall.shop.rpc.api.ShopGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopOrderInfoService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeOrderService;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.web.config.WebCachePrefix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Api(value = "商品系统", tags = "GoodsController")
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

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

    @RequestMapping(value = "/to_list", produces = "text/html", method = RequestMethod.GET)
    @ResponseBody
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String html = RedisUtil.get(WebCachePrefix.GOODS_LIST, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        model.addAttribute("goodsList", shopGoodsService.getGoodsDetailList());
        IWebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", context);
        RedisUtil.set(WebCachePrefix.GOODS_LIST, "", html);
        return html;
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String toDetail(Model model, UcenterUser user,
                           @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);
        ShopSpikeGoodsDetail shopSpikeGoodsDetail = shopGoodsService.getGoodsDetailByPrimaryKey(goodsId);
        model.addAttribute("shopGoods", shopSpikeGoodsDetail);
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
        model.addAttribute("status", status);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }


    @RequestMapping(value = "/spike",method = RequestMethod.POST)
    public String goodsSpike(Model model, UcenterUser user,
                             @RequestParam("goodsId") Long goodsId){
        if(null == user){
            return "login";
        }
        ShopSpikeGoodsDetail goods = shopGoodsService.getGoodsDetailByPrimaryKey(goodsId);
        if(goods.getSpikeStock() <= 0){
            model.addAttribute("result",new BaseResult(BaseResultEnum.SPIKE_ERROR,null));
            return "goods_spike_failed";
        }
        ShopSpikeOrderExample shopSpikeOrderExample = new ShopSpikeOrderExample();
        shopSpikeOrderExample.createCriteria().andUserIdEqualTo(user.getId());
        List<ShopSpikeOrder> shopSpikeOrders = shopSpikeOrderService.selectByExample(shopSpikeOrderExample);
        // 判断是否购买
        if(shopSpikeOrders.size() > 0 && null != shopSpikeOrders){
            model.addAttribute("result",new BaseResult(BaseResultEnum.PURCHASED_ERROR,null));
            return "goods_spike_failed";
        }
        ShopOrderInfo orderInfo = shopOrderInfoService.spikeGoods(user.getId(), goods);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goods);
        return "order_detail";
    }
}
