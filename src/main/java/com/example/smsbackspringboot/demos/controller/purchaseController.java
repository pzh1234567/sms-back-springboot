package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.service.purchaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class purchaseController {
    @Autowired
    com.example.smsbackspringboot.demos.service.purchaseService purchaseService;

    @ApiOperation(value="获取供应单列表")
    @GetMapping("/purchase/getPurchaseList")
    public Result getPurchaseList(String name, Integer pageNum, Integer pageSize){
        Result result = purchaseService.getPurchaseList(name, pageNum,pageSize);
        return result;
    }
}
