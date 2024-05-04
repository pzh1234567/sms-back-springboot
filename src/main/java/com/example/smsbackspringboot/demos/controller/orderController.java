package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Order;
import com.example.smsbackspringboot.demos.service.orderService;
import com.example.smsbackspringboot.demos.service.customerService;
import com.example.smsbackspringboot.demos.vo.param.orderQuerReqVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO订单controller
 */

@RestController
public class orderController {
    @Autowired
    orderService orderService;
//
//    @Autowired
//    customerService customerService;

    @ApiOperation(value = "获取订单列表")
    @GetMapping("/order/getOrderList")
    public Result getOrderList(String customerName,Long orderId,Integer pageNum,Integer pageSize,Integer status){
        System.out.println("111111111111orderList:1111111111111111111111111111111111111111111");
//        List<Long> idList = customerService.getCustomerByName(customerName);
        Result result = orderService.getOrderList(orderId,customerName,pageNum,pageSize,status);
        System.out.println("22222222222222222222222222222222222goodList:"+result);
        return result;

    }

}
