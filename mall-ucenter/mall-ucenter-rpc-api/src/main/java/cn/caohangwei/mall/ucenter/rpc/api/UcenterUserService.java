package cn.caohangwei.mall.ucenter.rpc.api;

import cn.caohangwei.mall.common.base.BaseService;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUserExample;

/**
* UcenterUserService接口
* Created by PinuoC on 2019/9/7.
*/
public interface UcenterUserService extends BaseService<UcenterUser, UcenterUserExample> {
    UcenterUser selectByPhone(String phone);
}