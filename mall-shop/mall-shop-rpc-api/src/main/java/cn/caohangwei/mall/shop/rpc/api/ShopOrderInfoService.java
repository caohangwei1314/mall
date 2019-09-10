package cn.caohangwei.mall.shop.rpc.api;

import cn.caohangwei.mall.common.base.BaseService;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.model.ShopGoods;
import cn.caohangwei.mall.shop.dao.model.ShopOrderInfo;
import cn.caohangwei.mall.shop.dao.model.ShopOrderInfoExample;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeGoods;

/**
* ShopOrderInfoService接口
* Created by PinuoC on 2019/9/9.
*/
public interface ShopOrderInfoService extends BaseService<ShopOrderInfo, ShopOrderInfoExample> {
    ShopOrderInfo goodsSpike(Long userId, ShopSpikeGoodsDetail goods);
}