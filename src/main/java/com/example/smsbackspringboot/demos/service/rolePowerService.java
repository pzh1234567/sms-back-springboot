package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Power;
import com.example.smsbackspringboot.demos.entiy.RolePower;
import com.example.smsbackspringboot.demos.mapper.powerMapper;
import com.example.smsbackspringboot.demos.mapper.rolePowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class rolePowerService {
    @Autowired
    rolePowerMapper rolePowerMapper;

    @Autowired
    powerMapper powerMapper;

    public Result getPowerListByRoleId(Long roleId){
        LambdaQueryWrapper<RolePower> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(roleId!=null,RolePower::getRoleId,roleId);
        List<RolePower> rolePowerList = rolePowerMapper.selectList(wrapper);
        List<Power> powerList = getPowerListbyId(rolePowerList);
        return Result.success(powerList);
    }

    public List<Power> getPowerListbyId(List<RolePower> rolePowerList){
        List<Power> powerList = new ArrayList<>();
        for (RolePower rolePower : rolePowerList) {
            //获取查询到列表中的orderId
            int powerId = rolePower.getPowerId();
            Power power = powerMapper.selectById(powerId);
            powerList.add(power);
        }
        return powerList;
    }
}
