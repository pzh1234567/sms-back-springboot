package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Customer;
import com.example.smsbackspringboot.demos.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class customerController {
    //注入mapper
    @Autowired
    customerService customerService;
    /**
     * 新增顾客信息
     */

    @PostMapping("/addCustomer")
    public Result addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return Result.success();
    }
    //    更新顾客信息
    @PostMapping("/updateCustomer")
    public Result updateCustomer(@RequestBody Customer customer){
        customerService.updateCustomer(customer);
        return Result.success();

    }
    //    删除顾客信息
    @DeleteMapping("/deleteCustomer/{id}")
    public Result deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
        return Result.success();
    }

    //    获取顾客员列表
    @GetMapping("/getCustomer")
    public Result getCustomer(){
        List<Customer> customerList=customerService.getCustomer();
        return Result.success(customerList);
    }
}
