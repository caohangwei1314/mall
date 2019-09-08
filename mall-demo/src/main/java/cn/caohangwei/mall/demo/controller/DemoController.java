package cn.caohangwei.mall.demo.controller;

import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.demo.config.DemoKey;
import cn.caohangwei.mall.demo.domain.User;
import cn.caohangwei.mall.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "Demo演示",tags = "DemoController")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ApiOperation("redis Object 赋值")
    @RequestMapping(value = "redis/setObject",method = RequestMethod.POST)
    public BaseResult redisSet(@RequestBody User value){
        RedisUtil.set(DemoKey.getById ,value.getId() + "",value);
        return new BaseResult(1,"success",null);
    }

    @ApiOperation("redis Integer 赋值")
    @RequestMapping(value = "redis/setInteger",method = RequestMethod.POST)
    public BaseResult redisSet(@RequestParam("id") Integer id){
        RedisUtil.set(DemoKey.getById,id+"",id);
        return new BaseResult(1,"success",null);
    }

    @ApiOperation("redis获取")
    @RequestMapping(value = "redis/get",method = RequestMethod.GET)
    public BaseResult redisGet(@RequestParam("value") String value){
        Integer result = RedisUtil.get(DemoKey.getById,value,Integer.class);
        return new BaseResult(1,"success",result);
    }

}
