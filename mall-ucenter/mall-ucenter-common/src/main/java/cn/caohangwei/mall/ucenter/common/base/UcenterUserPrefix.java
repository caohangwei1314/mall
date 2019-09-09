package cn.caohangwei.mall.ucenter.common.base;

import cn.caohangwei.mall.common.base.BasePrefix;

public class UcenterUserPrefix extends BasePrefix {

    public static final int DEFAULT_EXPIRE_TIME = 3600 * 24;

    public static final String DEFAULT_TOKEN_NAME = "token-";

    public static UcenterUserPrefix token = new UcenterUserPrefix(DEFAULT_TOKEN_NAME,DEFAULT_EXPIRE_TIME);

    public UcenterUserPrefix(String prefix){
        super(prefix);
    }

    public UcenterUserPrefix(String prefix,int expireTime){
        super(prefix, expireTime);
    }

}
