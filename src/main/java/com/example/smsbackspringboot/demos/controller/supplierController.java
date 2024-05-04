package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.service.supplierService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class supplierController {
    @Autowired
    supplierService supplierService;

    @ApiOperation(value="获取供应商列表")
    @GetMapping("/supplier/getSuppliersList")
    public Result getSupplierList(String name,int pageNum, int pageSize){
        Result result = supplierService.getSupplierList(name,pageNum,pageSize);
        return result;
    }
}
