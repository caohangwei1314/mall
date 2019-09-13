package cn.caohangwei.mall.shop.rpc.service.impl;

import cn.caohangwei.mall.common.annotation.BaseService;
import cn.caohangwei.mall.common.base.BaseServiceImpl;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.mapper.ShopGoodsMapper;
import cn.caohangwei.mall.shop.dao.model.ShopGoods;
import cn.caohangwei.mall.shop.dao.model.ShopGoodsExample;
import cn.caohangwei.mall.shop.rpc.api.ShopGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* ShopGoodsService实现
* Created by PinuoC on 2019/9/9.
*/
@Service
@BaseService
public class ShopGoodsServiceImpl extends BaseServiceImpl<ShopGoodsMapper, ShopGoods, ShopGoodsExample> implements ShopGoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopGoodsServiceImpl.class);

    @Autowired
    ShopGoodsMapper shopGoodsMapper;

    public List<ShopSpikeGoodsDetail> getGoodsDetailList() {
        return shopGoodsMapper.getShopSpikeGoodsDetailList();
    }

    public ShopSpikeGoodsDetail getGoodsDetailByPrimaryKey(Long id) {
        return shopGoodsMapper.getGoodsDetailByPrimaryKey(id);
    }
}