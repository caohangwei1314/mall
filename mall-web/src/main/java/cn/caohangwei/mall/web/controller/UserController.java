package cn.caohangwei.mall.web.controller;

import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/info")
    @ResponseBody
    public BaseResult userInfo(UcenterUser user){
        return new BaseResult(BaseResultEnum.SUCCESS,user);
    }
}
