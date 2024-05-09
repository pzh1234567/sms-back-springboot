package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Role;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.service.roleService;
import com.example.smsbackspringboot.demos.service.staffRoleService;
import com.example.smsbackspringboot.demos.util.BeanCopyUtils;
import com.example.smsbackspringboot.demos.vo.param.AddStaffParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class staffController {
    @Autowired
    com.example.smsbackspringboot.demos.service.staffService staffService;

    @Autowired
    com.example.smsbackspringboot.demos.service.staffRoleService staffRoleService;

    @Autowired
    com.example.smsbackspringboot.demos.service.roleService roleService;

    /**
     * 新增员工信息
     */
    @ApiOperation(value = "获取员工列表")
    @GetMapping("/user/staff/getstafflist")
    public Result addStaff(Long roleId, String name, Integer gender, Integer pageNum, Integer pageSize) {
        System.out.println("roleID11111" + roleId);
        Result result = staffService.getStaffList(roleId, name, gender, pageNum, pageSize);
        return result;
    }

    /**
     * 删除员工信息
     */
    @ApiOperation(value = "删除员工信息")
    @DeleteMapping("/user/staff/deleteStaff/{id}")
    public Result deleteStaffById(@PathVariable("id") Long id) {
        int count = staffService.deletedStaffById(id);
        if (count > 0) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation(value = "编辑员工信息")
    @PutMapping("/user/staff/editStaffInfo")
    public Result editStaffById(@RequestBody AddStaffParam addStaffParam) {
        System.out.println("2222222" + addStaffParam);
        int count = staffService.updateStaffInfoById(addStaffParam);
        if (count > 0) {
            return Result.success("编辑成功");
        } else {
            return Result.error("编辑失败");
        }
    }

    @ApiOperation(value = "添加员工信息")
    @PostMapping("/user/staff/addStaffInfo")
    public Result addStaffInfo(@RequestBody AddStaffParam addStaffParam) {
        System.out.println("2222222" + addStaffParam);

        int count = staffService.addStaffInfo(addStaffParam);
        if (count > 0) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
//        Role role = roleService.getRoleByName(addStaffParam.getRoleName());
//        int count = staffRoleService.addStaffRole(staffId, addStaffParam.getRoleId());
    }
}
