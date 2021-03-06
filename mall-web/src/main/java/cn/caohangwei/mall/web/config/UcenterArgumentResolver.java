package cn.caohangwei.mall.web.config;

import cn.caohangwei.mall.common.util.CookieUtil;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.ucenter.common.base.UcenterConstant;
import cn.caohangwei.mall.ucenter.common.base.UcenterUserPrefix;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.ucenter.rpc.api.UcenterUserService;
import cn.caohangwei.mall.web.base.UserContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class UcenterArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == UcenterUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return UserContext.getUser();
    }
}
