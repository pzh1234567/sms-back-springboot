package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.TStaffEntity;
import com.example.smsbackspringboot.demos.service.staffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class staffController {
    @Autowired
    com.example.smsbackspringboot.demos.service.staffService staffService;
    /**
     * 新增员工信息
     */

    @PostMapping("/addStaff")
    public Result addStaff(@RequestBody TStaffEntity staff){
        staffService.addStaff(staff);
        return Result.success();
    }
}
