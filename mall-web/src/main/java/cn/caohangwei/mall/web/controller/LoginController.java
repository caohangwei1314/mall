package cn.caohangwei.mall.web.controller;

import cn.caohangwei.mall.common.base.BaseController;
import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.common.util.*;
import cn.caohangwei.mall.ucenter.common.base.UcenterConstant;
import cn.caohangwei.mall.ucenter.common.base.UcenterUserLoginInfo;
import cn.caohangwei.mall.ucenter.common.base.UcenterUserPrefix;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.ucenter.rpc.api.UcenterUserService;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static cn.caohangwei.mall.ucenter.common.base.UcenterConstant.COOKIE_TOKEN_NAME;

@Api(value = "用户系统",tags = "LoginController")
@Controller
@RequestMapping("/ucenter")
public class LoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UcenterUserService ucenterUserService;

    @RequestMapping(value = "/to_login",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    @ApiOperation("登陆")
    @RequestMapping(value = "/do_login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult doLogin(UcenterUserLoginInfo record,
                              @CookieValue(value = COOKIE_TOKEN_NAME,required = false) String cookieToken,
                              @RequestParam(value = COOKIE_TOKEN_NAME,required = false) String paramToken,
                              HttpServletResponse response){
        LOGGER.info(record.toString());
        //判断是否登陆过
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        UcenterUser user = RedisUtil.get(UcenterUserPrefix.token,token,UcenterUser.class);
        if(user==null){
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
            user = ucenterUserService.selectByPhone(phone);
            if(null == user){
                return new BaseResult(BaseResultEnum.PHONE_NOT_EXISTS,null);
            }
            String dbPass = user.getPassword();
            String saltDB = user.getSalt();
            String calcPass = MD5Util.fromPassDBPass(formPass,saltDB);
            if(!calcPass.equals(dbPass)){
                return new BaseResult(BaseResultEnum.PASSWORD_ERROR,null);
            }
            CookieUtil.addCookie(response, COOKIE_TOKEN_NAME,UcenterUserPrefix.token,user);
        }else {
            CookieUtil.addCookie(response,token,UcenterConstant.COOKIE_TOKEN_NAME,UcenterUserPrefix.token,user);
        }
        return new BaseResult(BaseResultEnum.SUCCESS,user);
    }
}
