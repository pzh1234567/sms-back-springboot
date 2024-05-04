package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Staff;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class staffController {
    @Autowired
    com.example.smsbackspringboot.demos.service.staffService staffService;

    /**
     * 新增员工信息
     */
    @ApiOperation(value = "获取员工列表")
    @GetMapping("/user/staff/getstafflist")
    public Result addStaff(String name, Integer gender, Integer pageNum, Integer pageSize) {
        Result result = staffService.getStaffList(name, gender, pageNum, pageSize);
        return result;
    }

    /**
     * 删除员工信息
     */
    @ApiOperation(value = "删除员工信息")
    @DeleteMapping("/user/staff/deleteStaff/{id}")
    public Result deleteStaffById(@PathVariable("id") Long id) {
        Result result = staffService.deletedStaffById(id);
        return result;
    }

    @ApiOperation(value = "编辑员工信息")
    @PutMapping("/user/staff/editStaffInfo")
    public Result editStaffById(@RequestBody Staff staff) {
        System.out.println("2222222" + staff);
        Result result = staffService.updateStaffInfoById(staff);
        return result;
    }

    @ApiOperation(value = "添加员工信息")
    @PostMapping("/user/staff/addStaffInfo")
    public Result addStaffInfo(@RequestBody Staff staff) {
        System.out.println("2222222" + staff);
        Result result = staffService.addStaffInfo(staff);
        return result;
    }

    @ApiOperation(value = "根据id查询员工信息")
    @GetMapping("/user/staff/getstaffInfoById/{id}")
    public Result getUserInfoById(@PathVariable("id") Long id){
        System.out.println(id);
        Staff staff = staffService.getStaffInfoById(id);
        return Result.success(staff);
    }
}
