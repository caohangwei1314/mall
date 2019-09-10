package cn.caohangwei.mall.web.controller;

import cn.caohangwei.mall.common.base.BaseController;
import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.model.*;
import cn.caohangwei.mall.shop.rpc.api.ShopGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopOrderInfoService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeOrderService;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "商品系统",tags = "GoodsController")
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

    @ApiOperation("商品列表")
    @RequestMapping(value = "/to_list",method = RequestMethod.GET)
    public String toList(Model model){
        model.addAttribute("goodsList",shopGoodsService.getGoodsDetailList());
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String toDetail(Model model, UcenterUser user,
                           @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);
        ShopSpikeGoodsDetail shopSpikeGoodsDetail = shopGoodsService.getGoodsDetailByPrimaryKey(goodsId);
        model.addAttribute("shopGoods",shopSpikeGoodsDetail);
        long startAt = shopSpikeGoodsDetail.getSpikeStartTime().getTime();
        long endAt = shopSpikeGoodsDetail.getSpikeEndTime().getTime();
        long now = System.currentTimeMillis();
        int status = 0;
        int remainSeconds = 0;
        if(now < startAt){
            status = 0;
            remainSeconds = (int)(startAt - now) / 1000;
        }else if (now > endAt){
            status = 2;
            remainSeconds = -1;
        }else {
            status = 1;
            remainSeconds = 0;
        }
        model.addAttribute("status",status);
        model.addAttribute("remainSeconds",remainSeconds);
        return "goods_detail";
    }

    @RequestMapping("/spike")
    public String goodsSpike(Model model, UcenterUser user,
                             @RequestParam("goodsId") Long goodsId){
        if(null == user){
            return "login";
        }
        ShopSpikeGoodsDetail goods = shopGoodsService.getGoodsDetailByPrimaryKey(goodsId);
        if(null == goods){
            model.addAttribute("result",new BaseResult(BaseResultEnum.GOODS_ERROR,null));
            return "goods_spike_failed";
        }
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
        ShopOrderInfo orderInfo = shopOrderInfoService.goodsSpike(user.getId(),goods);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goods);
        return "order_detail";
    }
}
