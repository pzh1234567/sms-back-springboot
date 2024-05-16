package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.service.memberRedemptionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class memberRedemptionController {
    @Autowired
    com.example.smsbackspringboot.demos.service.memberRedemptionService memberRedemptionService;

    @ApiOperation(value="获取会员兑换商品")
    @GetMapping("/memberRedemption/getMemberRedemptionList")
    public Result getMemberRedemptionList(){
        Result result = memberRedemptionService.getMemberRedemptionList();
        return result;
    }
}
