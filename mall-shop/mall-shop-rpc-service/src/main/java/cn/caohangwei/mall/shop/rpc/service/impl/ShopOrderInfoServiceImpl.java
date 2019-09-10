package cn.caohangwei.mall.shop.rpc.service.impl;

import cn.caohangwei.mall.common.annotation.BaseService;
import cn.caohangwei.mall.common.base.BaseServiceImpl;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.mapper.ShopOrderInfoMapper;
import cn.caohangwei.mall.shop.dao.model.*;
import cn.caohangwei.mall.shop.rpc.api.ShopGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopOrderInfoService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
* ShopOrderInfoService实现
* Created by PinuoC on 2019/9/9.
*/
@Service
@Transactional
@BaseService
public class ShopOrderInfoServiceImpl extends BaseServiceImpl<ShopOrderInfoMapper, ShopOrderInfo, ShopOrderInfoExample> implements ShopOrderInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopOrderInfoServiceImpl.class);

    @Autowired
    ShopOrderInfoMapper shopOrderInfoMapper;

    @Autowired
    ShopGoodsService shopGoodsService;

    @Autowired
    ShopSpikeGoodsService shopSpikeGoodsService;

    @Autowired
    ShopSpikeOrderService shopSpikeOrderService;

    public ShopOrderInfo goodsSpike(Long userId, ShopSpikeGoodsDetail goods) {
        ShopSpikeGoods shopSpikeGoods = new ShopSpikeGoods();
        shopSpikeGoods.setSpikeStock(goods.getSpikeStock()-1);
        ShopSpikeGoodsExample shopSpikeGoodsExample = new ShopSpikeGoodsExample();
        shopSpikeGoodsExample.createCriteria().andGoodsIdEqualTo(goods.getId());
        shopSpikeGoodsService.updateByExampleSelective(shopSpikeGoods,shopSpikeGoodsExample);
        ShopOrderInfo orderInfo = new ShopOrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setDeliveryAddressId(0L);
        orderInfo.setGoodsName(goods.getName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goods.getSpikePrice());
        orderInfo.setOrderChannel((byte)0);
        orderInfo.setStatus((byte) 0);
        orderInfo.setCreateDate(new Date());
        shopOrderInfoMapper.insertSelective(orderInfo);
        ShopSpikeOrder shopSpikeOrder = new ShopSpikeOrder();
        shopSpikeOrder.setGoodsId(goods.getId());
        shopSpikeOrder.setOrderId(orderInfo.getId());
        shopSpikeOrder.setUserId(userId);
        shopSpikeOrderService.insert(shopSpikeOrder);
        return orderInfo;
    }
}