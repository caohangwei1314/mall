package cn.caohangwei.mall.shop.rpc.service.impl;

import cn.caohangwei.mall.common.annotation.BaseService;
import cn.caohangwei.mall.common.base.BaseServiceImpl;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.shop.common.base.ShopGoodsPrefix;
import cn.caohangwei.mall.shop.dao.mapper.ShopSpikeOrderMapper;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrder;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrderExample;
import cn.caohangwei.mall.shop.rpc.api.ShopSpikeOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public ShopSpikeOrder getSpikeOrderByUserIdGoodsId(Long userId, Long goodsId) {
        ShopSpikeOrderExample example = new ShopSpikeOrderExample();
        example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
        List<ShopSpikeOrder> list = shopSpikeOrderMapper.selectByExample(example);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    public Long getSpikeResult(Long userId, Long goodsId) {
        ShopSpikeOrder order = getSpikeOrderByUserIdGoodsId(userId, goodsId);
        if(order != null){
            return order.getOrderId();
        }else {
            boolean flag = RedisUtil.exists(ShopGoodsPrefix.SPIKE_ORDER_OVER,""+goodsId);
            if(flag){
                return -1L;
            }else {
                return 0L;
            }
        }
    }
}