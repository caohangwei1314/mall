package cn.caohangwei.mall.shop.rpc.service.impl;

import cn.caohangwei.mall.common.annotation.BaseService;
import cn.caohangwei.mall.common.base.BaseServiceImpl;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.mapper.ShopGoodsMapper;
import cn.caohangwei.mall.shop.dao.mapper.ShopOrderInfoMapper;
import cn.caohangwei.mall.shop.dao.mapper.ShopSpikeGoodsMapper;
import cn.caohangwei.mall.shop.dao.mapper.ShopSpikeOrderMapper;
import cn.caohangwei.mall.shop.dao.model.*;
import cn.caohangwei.mall.shop.rpc.api.ShopOrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * ShopOrderInfoService实现
 * Created by PinuoC on 2019/9/9.
 */
@Service
@BaseService
public class ShopOrderInfoServiceImpl extends BaseServiceImpl<ShopOrderInfoMapper, ShopOrderInfo, ShopOrderInfoExample> implements ShopOrderInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopOrderInfoServiceImpl.class);

    @Autowired
    ShopOrderInfoMapper shopOrderInfoMapper;

    @Autowired
    ShopGoodsMapper shopGoodsMapper;

    @Autowired
    ShopSpikeGoodsMapper shopSpikeGoodsMapper;

    @Autowired
    ShopSpikeOrderMapper shopSpikeOrderMapper;

    @Transactional
    public ShopOrderInfo spikeGoods(Long userId, ShopSpikeGoodsDetail goods) {
        shopSpikeGoodsMapper.incrGoodsStockByGoodsId(goods.getId());
        ShopOrderInfo orderInfo = new ShopOrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setDeliveryAddressId(0L);
        orderInfo.setGoodsName(goods.getName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goods.getSpikePrice());
        orderInfo.setOrderChannel((byte) 0);
        orderInfo.setStatus((byte) 0);
        orderInfo.setCreateDate(new Date());
        shopOrderInfoMapper.insertSelective(orderInfo);
        ShopSpikeOrder shopSpikeOrder = new ShopSpikeOrder();
        shopSpikeOrder.setGoodsId(goods.getId());
        shopSpikeOrder.setOrderId(orderInfo.getId());
        shopSpikeOrder.setUserId(userId);
        shopSpikeOrderMapper.insert(shopSpikeOrder);
        return orderInfo;
    }
}