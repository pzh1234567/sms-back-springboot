package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Role;
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
     * 根据查询条件获取商品信息
     * @param
     * @return
     */
    public Result getRoleList(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Role> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Role::getRoleName,name);
        Page<Role> page = new Page<Role>(pageNum,pageSize);
        IPage<Role> roleIPage = roleMapper.selectPage(page, wrapper);
        List<Role> result = roleIPage.getRecords();
        System.out.println(result);


        return Result.success(new PageVo(roleIPage.getRecords(),roleIPage.getTotal()));
    }
}
