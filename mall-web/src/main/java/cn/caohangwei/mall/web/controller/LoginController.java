package cn.caohangwei.mall.ucenter.web.controller;

import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.common.util.MD5Util;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.common.util.UUIDUtil;
import cn.caohangwei.mall.common.util.ValidatorUtil;
import cn.caohangwei.mall.ucenter.common.base.UcenterUserPrefix;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.ucenter.rpc.api.UcenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Api(value = "用户系统",tags = "LoginController")
@Controller
@RequestMapping("/ucenter")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private static final String COOKIE_TOKEN_NAME = "ucenter-token";

    @Autowired
    private UcenterUserService ucenterUserService;

    @RequestMapping(value = "/to_login",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    @ApiOperation("登陆")
    @RequestMapping(value = "/do_login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult doLogin(UcenterUser record, HttpServletResponse response){
        LOGGER.info(record.toString());
        //参数校验
        String formPass = record.getPassword();
        String phone = record.getPhone();
        if(StringUtils.isEmpty(formPass)){
            return new BaseResult(BaseResultEnum.PASSWORD_EMPTY,null);
        }
        if(StringUtils.isEmpty(phone)){
            return new BaseResult(BaseResultEnum.PHONE_EMPTY,null);
        }
        if(!ValidatorUtil.isPhone(phone)){
            return new BaseResult(BaseResultEnum.PHONE_ERROR,null);
        }
        UcenterUser user = ucenterUserService.selectByPhone(phone);
        if(null == user){
            return new BaseResult(BaseResultEnum.PHONE_NOT_EXISTS,null);
        }
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.fromPassDBPass(formPass,saltDB);
        if(!calcPass.equals(dbPass)){
            return new BaseResult(BaseResultEnum.PASSWORD_ERROR,null);
        }
        //生成cookie
        String token = UUIDUtil.randomUUID();
        RedisUtil.set(UcenterUserPrefix.token,token,user);
        Cookie cookie = new Cookie(COOKIE_TOKEN_NAME,token);
        cookie.setMaxAge(UcenterUserPrefix.token.getExpireTime());
        cookie.setPath("/");
        response.addCookie(cookie);
        return new BaseResult(BaseResultEnum.SUCCESS,user);
    }
}
