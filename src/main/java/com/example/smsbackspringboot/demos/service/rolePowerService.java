package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Power;
import com.example.smsbackspringboot.demos.entiy.Role;
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

    /**
     * 删除角色下的权限
     * @param roleId
     * @param powerId
     * @return
     */
    public int deleteRolePower(Long roleId,Long powerId){
        LambdaQueryWrapper<RolePower> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(roleId!=null,RolePower::getRoleId,roleId);
        wrapper.eq(powerId!=null,RolePower::getPowerId,powerId);
        int flag = rolePowerMapper.delete(wrapper);
        return flag;
    }

    public int addRolePower(Long roleId, int powerId){
        RolePower rolePower = new RolePower();
        rolePower.setPowerId(powerId);
        rolePower.setRoleId(roleId);
        int count = rolePowerMapper.insert(rolePower);
        return count;
    }
}
