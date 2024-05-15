package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Customer;
import com.example.smsbackspringboot.demos.service.customerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class customerController {
    //注入mapper
    @Autowired
    customerService customerService;
    /**
     * 获取顾客员列表
     */
    @ApiOperation(value = "获取会员列表")
    @GetMapping("/customer/getCustomer")
    public Result getCustomer(Integer level, String name, Integer pageNum, Integer pageSize){
        Result result =customerService.getCustomerList(level,name, pageNum,pageSize);
        return result;
    }

    /**
     * 更新顾客信息
     */
    @ApiOperation(value = "更新会员信息")
    @PutMapping("/customer/updateCustomer")
    public Result UpdataCustomerInfoById(@RequestBody Customer customer){
        System.out.println("customerList:"+customer);
        int result =customerService.updateCustomerInfoById(customer);
        if(result>0){
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    /**
     * 添加会员信息
     */
    @ApiOperation(value = "更新会员信息")
    @PostMapping("/customer/addCustomer")
    public Result addCustomerInfo(@RequestBody Customer customer){
        System.out.println("customerList:"+customer);
        Result result =customerService.addCustomerInfo(customer);
        return result;
    }

    /**
     * 删除会员信息
     */
    @ApiOperation(value = "删除会员信息")
    @DeleteMapping("/customer/deleteCustomer/{id}")
    public Result deleteCustomerInfo(@PathVariable Long id){
        Result result =customerService.deletedCustomerById(id);
        return result;
    }

    /**
     * 根据会员电话查找会员
     */
    @ApiOperation(value = "根据会员电话查找会员信息")
    @GetMapping("/customer/getCustomerInfoByPhone/{phone}")
    public Result getCustomerInfoByPhone(@PathVariable String phone){
        Customer customer = customerService.getCustomerByPhone(phone);
        return Result.success(customer);
    }
}
