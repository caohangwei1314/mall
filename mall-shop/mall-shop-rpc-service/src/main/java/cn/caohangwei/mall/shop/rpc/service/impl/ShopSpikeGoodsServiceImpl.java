package cn.caohangwei.mall.shop.rpc.service.impl;

import cn.caohangwei.mall.common.annotation.BaseService;
import cn.caohangwei.mall.common.base.BaseServiceImpl;
import cn.caohangwei.mall.shop.dao.mapper.ShopSpikeGoodsMapper;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeGoods;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeGoodsExample;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* ShopSpikeGoodsService实现
* Created by PinuoC on 2019/9/9.
*/
@Service
@Transactional
@BaseService
public class ShopSpikeGoodsServiceImpl extends BaseServiceImpl<ShopSpikeGoodsMapper, ShopSpikeGoods, ShopSpikeGoodsExample> implements ShopSpikeGoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopSpikeGoodsServiceImpl.class);

    @Autowired
    ShopSpikeGoodsMapper shopSpikeGoodsMapper;

    public ShopSpikeGoods selectByGoodsId(Long goodsId) {
        ShopSpikeGoodsExample example = new ShopSpikeGoodsExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        List<ShopSpikeGoods> list = shopSpikeGoodsMapper.selectByExample(example);
        if(null != list && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}