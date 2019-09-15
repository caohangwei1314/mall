package cn.caohangwei.mall.web.controller;

import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.shop.dao.model.ShopGoods;
import cn.caohangwei.mall.shop.dao.model.ShopOrderInfo;
import cn.caohangwei.mall.shop.rpc.api.ShopGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopOrderInfoService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeOrderService;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.web.config.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    ShopOrderInfoService shopOrderInfoService;

    @Autowired
    ShopSpikeOrderService shopSpikeOrderService;

    @Autowired
    ShopGoodsService shopGoodsService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult detail(UcenterUser user, @RequestParam("orderId") Long orderId){
        if(null == user){
            return new BaseResult(BaseResultEnum.SESSION_ERROR,null);
        }
        ShopOrderInfo orderInfo = shopOrderInfoService.selectByPrimaryKey(orderId);
        if(null == orderId){
            return new BaseResult(BaseResultEnum.ORDER_NOT_EXISTS,null);
        }
        ShopGoods goods = shopGoodsService.selectByPrimaryKey(orderInfo.getGoodsId());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setShopGoods(goods);
        orderDetail.setShopOrderInfo(orderInfo);
        return new BaseResult(BaseResultEnum.SUCCESS,orderDetail);
    }
}
