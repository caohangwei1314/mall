package cn.caohangwei.mall.web.config;

import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.common.util.CookieUtil;
import cn.caohangwei.mall.common.util.JSONUtil;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.shop.common.base.ShopGoodsPrefix;
import cn.caohangwei.mall.ucenter.common.base.UcenterConstant;
import cn.caohangwei.mall.ucenter.common.base.UcenterUserPrefix;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.web.base.UserContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            UcenterUser user = getUser(request, response);
            UserContext.setUser(user);
            HandlerMethod hm = (HandlerMethod) handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if(needLogin){
                if(null == user){
                    render(response, new BaseResult(BaseResultEnum.SESSION_ERROR, null));
                    return false;
                }
                key +=  "_" + user.getId();
            }
            ShopGoodsPrefix prefix = ShopGoodsPrefix.spikeAccess(seconds);
            Integer count = RedisUtil.get(prefix, key, Integer.class);
            if (null == count) {
                RedisUtil.set(prefix, key, 1);
            }else if (count < maxCount){
                RedisUtil.incr(prefix, key);
            }else {
                render(response,new BaseResult(BaseResultEnum.ACCESS_ERROR, null));
                return false;
            }
        }
        return true;
    }

    private void render(HttpServletResponse response,BaseResult msg) throws Exception{
        response.setContentType("application/json;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        String str = JSONUtil.beanToString(msg);
        outputStream.write(str.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }

    private UcenterUser getUser(HttpServletRequest request,HttpServletResponse response){
        String paramToken = request.getParameter(UcenterConstant.COOKIE_TOKEN_NAME);
        String cookieToken = null;
        if(request.getCookies() != null && request.getCookies().length > 0){
            for (Cookie cookie : request.getCookies()) {
                if (UcenterConstant.COOKIE_TOKEN_NAME.equals(cookie.getName())) {
                    cookieToken = cookie.getValue();
                }
            }
        }
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        UcenterUser user = RedisUtil.get(UcenterUserPrefix.token,token,UcenterUser.class);
        CookieUtil.addCookie(response,token,UcenterConstant.COOKIE_TOKEN_NAME,UcenterUserPrefix.token,user);
        return user;
    }
}
