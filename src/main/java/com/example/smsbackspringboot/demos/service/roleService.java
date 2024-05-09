package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Role;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.mapper.roleMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roleService {

    @Autowired
    roleMapper roleMapper;

    /**
     * 根据查询条件获取角色信息
     *
     * @param
     * @return
     */
    public Result getRoleList(String name) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, Role::getRoleName, name);
        List<Role> roleList = roleMapper.selectList(wrapper);
        System.out.println(roleList);
        return Result.success(roleList);
    }

    /**
     * 根据角色名获取角色信息
     * @param name
     * @return
     */

    public Role getRoleByName(String name) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(name != null, Role::getRoleName, name);
        Role role = roleMapper.selectOne(wrapper);
        return role;
    }
}
