package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Power;
import com.example.smsbackspringboot.demos.service.powerService;
import com.example.smsbackspringboot.demos.service.rolePowerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class powerController {
    @Autowired
    com.example.smsbackspringboot.demos.service.powerService powerService;

    @ApiOperation(value="查询所有权限")
    @GetMapping("/power/getPowerList")
    public Result getPowerList(){
        List<Power> powerList = powerService.getPowerList();
        return Result.success(powerList);
    }


}
