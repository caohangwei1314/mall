package cn.caohangwei.mall.shop.controller;

import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.ucenter.common.base.UcenterUserPrefix;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "商品系统",tags = "GoodsController")
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @ApiOperation("商品列表")
    @RequestMapping(value = "/to_list",method = RequestMethod.GET)
    public String toList(Model model, @CookieValue(value = UcenterUserPrefix.DEFAULT_TOKEN_NAME,required = false) String cookieToken,
                         @RequestParam(value = UcenterUserPrefix.DEFAULT_TOKEN_NAME,required = false) String paramToken,
                         HttpServletResponse response){
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return "http://localhost:8989/ucenter/to_login";
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        UcenterUser user = RedisUtil.get(UcenterUserPrefix.token,token,UcenterUser.class);
        model.addAttribute("user",user);
        return "goods_list";
    }

}
