package cn.caohangwei.mall.demo.config;

import cn.caohangwei.mall.common.base.BasePrefix;

public class DemoKey extends BasePrefix {

    public static DemoKey getById = new DemoKey("id");

    public static DemoKey getByName = new DemoKey("name");

    public DemoKey(String prefix){
        super(prefix);
    }

}
