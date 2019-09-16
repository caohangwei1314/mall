package cn.caohangwei.mall.shop.rpc.api;

import cn.caohangwei.mall.common.base.BaseService;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrder;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrderExample;

/**
* ShopSpikeOrderService接口
* Created by PinuoC on 2019/9/9.
*/
public interface ShopSpikeOrderService extends BaseService<ShopSpikeOrder, ShopSpikeOrderExample> {

    ShopSpikeOrder getSpikeOrderByUserIdGoodsId(Long userId,Long goodsId);

    Long getSpikeResult(Long userId,Long goodsId);
}