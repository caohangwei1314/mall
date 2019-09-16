package cn.caohangwei.mall.web.base;

import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;

public class UserContext {

    private static ThreadLocal<UcenterUser> userThreadLocal = new ThreadLocal<UcenterUser>();

    public static void setUser(UcenterUser user){
        userThreadLocal.set(user);
    }

    public static UcenterUser getUser(){
        return userThreadLocal.get();
    }
}
