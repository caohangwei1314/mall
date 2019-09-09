package cn.caohangwei.mall.web.controller;

import cn.caohangwei.mall.common.base.BaseController;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "商品系统",tags = "GoodsController")
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    @ApiOperation("商品列表")
    @RequestMapping(value = "/to_list",method = RequestMethod.GET)
    public String toList(Model model, UcenterUser user){
        model.addAttribute("user",user);
        return "goods_list";
    }
}
