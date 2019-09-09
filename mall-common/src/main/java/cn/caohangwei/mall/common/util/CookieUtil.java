package cn.caohangwei.mall.common.util;

import cn.caohangwei.mall.common.base.BasePrefix;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * 添加 Cookie
     * @param response 响应头
     * @param cookieName Cookie名称
     * @param prefix Redis前缀
     * @param value 保存的值
     */
    public static <T> void addCookie(HttpServletResponse response, String cookieName, BasePrefix prefix, T value){
        addCookie(response,UUIDUtil.randomUUID(),cookieName,prefix,value);
    }

    /**
     * 刷新 Cookie
     * @param response 响应头
     * @param key 键名
     * @param cookieName Cookie名称
     * @param prefix Redis前缀
     * @param value 保存的值
     */
    public static <T> void addCookie(HttpServletResponse response,String key, String cookieName, BasePrefix prefix, T value){
        if(null == value || null == prefix || null == cookieName || null == key || null == response){
            return;
        }
        RedisUtil.set(prefix,key,value);
        Cookie cookie = new Cookie(cookieName,key);
        cookie.setPath("/");
        cookie.setMaxAge(prefix.getExpireTime());
        response.addCookie(cookie);
    }

}
