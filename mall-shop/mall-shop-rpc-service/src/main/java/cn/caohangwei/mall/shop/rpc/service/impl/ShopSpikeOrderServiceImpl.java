package cn.caohangwei.mall.shop.rpc.service.impl;

import cn.caohangwei.mall.common.annotation.BaseService;
import cn.caohangwei.mall.common.base.BaseServiceImpl;
import cn.caohangwei.mall.shop.dao.mapper.ShopSpikeOrderMapper;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrder;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrderExample;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* ShopSpikeOrderService实现
* Created by PinuoC on 2019/9/9.
*/
@Service
@BaseService
public class ShopSpikeOrderServiceImpl extends BaseServiceImpl<ShopSpikeOrderMapper, ShopSpikeOrder, ShopSpikeOrderExample> implements ShopSpikeOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopSpikeOrderServiceImpl.class);

    @Autowired
    ShopSpikeOrderMapper shopSpikeOrderMapper;

}