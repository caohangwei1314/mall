package cn.caohangwei.mall.shop.common.base;

import cn.caohangwei.mall.common.base.BasePrefix;

public class ShopGoodsPrefix extends BasePrefix {

    public static final int DEFAULT_EXPIRE_TIME = 60;

    public static ShopGoodsPrefix GOODS_LIST = new ShopGoodsPrefix("goods-list",DEFAULT_EXPIRE_TIME);

    public static ShopGoodsPrefix SPIKE_ORDER = new ShopGoodsPrefix("spike-order",0);

    public static ShopGoodsPrefix SPIKE_GOODS_STOCK = new ShopGoodsPrefix("spike-goods-stock",0);

    public static ShopGoodsPrefix SPIKE_ORDER_OVER = new ShopGoodsPrefix("spike-order-over",0);

    public static ShopGoodsPrefix SPIKE_GOODS_PATH = new ShopGoodsPrefix("spike-goods-path",60);

    public ShopGoodsPrefix(String prefix, int expireTime){super(prefix,expireTime);}
}
