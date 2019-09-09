package cn.caohangwei.mall.ucenter.rpc.service.impl;

import cn.caohangwei.mall.common.annotation.BaseService;
import cn.caohangwei.mall.common.base.BaseServiceImpl;
import cn.caohangwei.mall.ucenter.dao.mapper.UcenterUserMapper;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUserExample;
import cn.caohangwei.mall.ucenter.rpc.api.UcenterUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UcenterUserService实现
* Created by PinuoC on 2019/9/7.
*/
@Service
@Transactional
@BaseService
public class UcenterUserServiceImpl extends BaseServiceImpl<UcenterUserMapper, UcenterUser, UcenterUserExample> implements UcenterUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UcenterUserServiceImpl.class);

    @Autowired
    UcenterUserMapper ucenterUserMapper;

    @Override
    public UcenterUser selectByPhone(String phone) {
        UcenterUserExample ucenterUserExample = new UcenterUserExample();
        ucenterUserExample.createCriteria().andPhoneEqualTo(phone);
        List<UcenterUser> userList = selectByExample(ucenterUserExample);
        if(null != userList && userList.size()>=1){
            return userList.get(0);
        }
        return null;
    }
}