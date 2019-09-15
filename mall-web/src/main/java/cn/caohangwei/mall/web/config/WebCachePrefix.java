package cn.caohangwei.mall.web.config;

import cn.caohangwei.mall.common.base.BasePrefix;

public class WebCachePrefix extends BasePrefix {

    public static final int DEFAULT_EXPIRE_TIME = 60;

    public static WebCachePrefix GOODS_LIST = new WebCachePrefix("goods-list",DEFAULT_EXPIRE_TIME);

    public static WebCachePrefix SPIKE_ORDER = new WebCachePrefix("spike-order",0);

    public WebCachePrefix(String prefix, int expireTime){super(prefix,expireTime);}
}
