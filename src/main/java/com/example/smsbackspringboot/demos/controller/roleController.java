package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.service.rolePowerService;
import com.example.smsbackspringboot.demos.service.roleService;
import com.example.smsbackspringboot.demos.service.staffRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class roleController {
    @Autowired
    roleService roleService;

    @Autowired
    staffRoleService staffRoleService;

    @Autowired
    rolePowerService rolePowerService;

    @ApiOperation(value ="获取角色列表")
    @GetMapping("/user/role/getRoleList")
    public Result getRoleList(String roleName, Integer pageNum, Integer pageSize){
        Result result = roleService.getRoleList(roleName,pageNum,pageSize);
        return result;
    }

    @ApiOperation(value ="获取角色用户")
    @GetMapping("/user/role/getStaffListById/{id}")
    public Result getStaffListByRoleId(@PathVariable("id") Long roleId){
        Result result = staffRoleService.getStaffListbyRoleId(roleId);
        return result;
    }

    @ApiOperation(value ="获取角色权限")
    @GetMapping("/user/role/getPowerListById/{id}")
    public Result getPowerListByRoleId(@PathVariable("id") Long roleId){
        Result result = rolePowerService.getPowerListByRoleId(roleId);
        return result;
    }
}
