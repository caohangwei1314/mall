package cn.caohangwei.mall.shop.rpc.api;

import cn.caohangwei.mall.common.base.BaseService;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.model.ShopGoods;
import cn.caohangwei.mall.shop.dao.model.ShopGoodsExample;

import java.util.List;

/**
* ShopGoodsService接口
* Created by PinuoC on 2019/9/9.
*/
public interface ShopGoodsService extends BaseService<ShopGoods, ShopGoodsExample> {

    List<ShopSpikeGoodsDetail> getGoodsDetailList();

    ShopSpikeGoodsDetail getGoodsDetailByPrimaryKey(Long id);
}