package cn.caohangwei.mall.shop.rpc.api;

import cn.caohangwei.mall.common.base.BaseService;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeGoods;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeGoodsExample;

/**
* ShopSpikeGoodsService接口
* Created by PinuoC on 2019/9/9.
*/
public interface ShopSpikeGoodsService extends BaseService<ShopSpikeGoods, ShopSpikeGoodsExample> {
    ShopSpikeGoods selectByGoodsId(Long goodsId);
}