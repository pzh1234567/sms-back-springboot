package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Role;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.entiy.StaffRole;
import com.example.smsbackspringboot.demos.service.rolePowerService;
import com.example.smsbackspringboot.demos.service.roleService;
import com.example.smsbackspringboot.demos.service.staffRoleService;
import com.example.smsbackspringboot.demos.vo.param.AddRolePowerParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result getRoleList(String roleName){
        Result result = roleService.getRoleList(roleName);
        return result;
    }

    @ApiOperation(value ="获取角色用户")
    @GetMapping("/user/role/getStaffListById/{id}")
    public Result getStaffListByRoleId(@PathVariable("id") Long roleId){
        List<Staff> staffList = staffRoleService.getStaffListbyRoleId(roleId);
        return Result.success(staffList);
    }

    @ApiOperation(value ="获取角色权限")
    @GetMapping("/user/role/getPowerListById/{id}")
    public Result getPowerListByRoleId(@PathVariable("id") Long roleId){
        Result result = rolePowerService.getPowerListByRoleId(roleId);
        return result;
    }

    @ApiOperation(value="添加角色信息")
    @PostMapping("/user/role/addRole")
    public Result addRole(@RequestBody Role role){
        Result result = roleService.addRole(role);
        return result;
    }

    @ApiOperation(value="编辑角色信息")
    @PutMapping("/user/role/updateRole")
    public Result updateRole(@RequestBody Role role){
        Result result = roleService.updateRole(role);
        return result;
    }

    @ApiOperation(value="删除角色信息")
    @DeleteMapping("/user/role/deleteRole/{id}")
    public Result deleteRole(@PathVariable Long id){
        //判断是否有员工是这个角色
        List<Staff> staffList = staffRoleService.getStaffListbyRoleId(id);
        if(staffList.size() !=0){
            return Result.error("还有员工任职在这个岗位，禁止删除");
        }
        int count = roleService.deleteRole(id);
//        int flag = rolePowerService.deleteRolePower(id);
        if(count > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    /**
     * 删除角色员工
     * @param roleId
     * @param staffId
     * @return
     */
    @ApiOperation(value="删除角色员工信息")
    @DeleteMapping("/user/role/deleteRoleStaff")
    public Result deleteRoleStaff(Long roleId,Long staffId){
        int count = staffRoleService.deleteRoleStaff(roleId,staffId);
        if(count > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    /**
     * 删除角色权限
     * @param roleId
     * @param powerId
     * @return
     */
    @ApiOperation(value="删除角色权限信息")
    @DeleteMapping("/user/role/deleteRolePower")
    public Result deleteRolePower(Long roleId,Long powerId){
        int count = rolePowerService.deleteRolePower(roleId, powerId);
        if(count > 0){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    /**
     * 添加角色权限
     * @param addRolePowerParam
     * @return
     */
    @ApiOperation(value = "添加角色权限")
    @PostMapping("/user/role/addRolePower")
    public Result addRolePowers(@RequestBody AddRolePowerParam addRolePowerParam){
        int flag = 1;
        for (Integer powerId : addRolePowerParam.getPowerIdList()){
            int count = rolePowerService.addRolePower(addRolePowerParam.getRoleId(),powerId);
            flag = flag*count;
        }
        if(flag > 0){
            return Result.success("添加权限成功");
        }else {
            return Result.error("添加权限失败");
        }
    }

    @ApiOperation(value="根据员工id获取角色信息")
    @GetMapping("/user/role/getRoleByStaffId/{id}")
    public Result getRolebyStaffId(@PathVariable Long id){
        StaffRole staffRole = staffRoleService.getStaffRoleByStaffId(id);
        Role role = roleService.getRoleById(staffRole.getRoleId());
        return Result.success(role);
    }

}
