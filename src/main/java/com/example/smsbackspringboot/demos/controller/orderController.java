package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Order;
import com.example.smsbackspringboot.demos.service.orderGoodsService;
import com.example.smsbackspringboot.demos.service.orderService;
import com.example.smsbackspringboot.demos.vo.param.AddOrderParam;
import com.example.smsbackspringboot.demos.vo.param.GoodItemParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description TODO订单controller
 */

@RestController
public class orderController {
    @Autowired
    orderService orderService;

    @Autowired
    orderGoodsService orderGoodsService;


    @ApiOperation(value = "获取订单列表")
    @GetMapping("/order/getOrderList")
    public Result getOrderList(String customerName, Long orderId, Integer pageNum, Integer pageSize, Integer status) {
        System.out.println("111111111111orderList:1111111111111111111111111111111111111111111");
//        List<Long> idList = customerService.getCustomerByName(customerName);
        Result result = orderService.getOrderList(orderId, customerName, pageNum, pageSize, status);
        System.out.println("22222222222222222222222222222222222goodList:" + result);
        return result;

    }

    @ApiOperation(value = "添加订单详情")
    @PostMapping("/order/addOrderInfo")
    public Result addOrderInfo(@RequestBody AddOrderParam orderInfo) {
        Order order = new Order();
        order.setOrderStatus(orderInfo.getOrderStatus());
        order.setCustomerName(orderInfo.getCustomerName());
        order.setCreatetime(orderInfo.getCreateTime());
        order.setPaytime(orderInfo.getPayTime());
        Long orderId = orderService.addOrder(order);
        int result = 1;
        for (GoodItemParam item : orderInfo.getGoodsList()) {
            Long goodsId = item.getGoodId();
            int num = item.getNum();
            int count = orderGoodsService.addOrderGoods(orderId,goodsId,num);
            result = count * result;
        }
        if(result == 0){
            Result.error("添加订单失败");
        }
        return Result.success(result);
    }

}
