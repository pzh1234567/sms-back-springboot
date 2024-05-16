package com.example.smsbackspringboot.demos.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Customer;
import com.example.smsbackspringboot.demos.entiy.Order;
import com.example.smsbackspringboot.demos.service.customerService;
import com.example.smsbackspringboot.demos.service.goodsService;
import com.example.smsbackspringboot.demos.service.orderGoodsService;
import com.example.smsbackspringboot.demos.service.orderService;
import com.example.smsbackspringboot.demos.vo.commom.GoodsItemVo;
import com.example.smsbackspringboot.demos.vo.commom.OrderInfoVo;
import com.example.smsbackspringboot.demos.vo.commom.SaleDetailVo;
import com.example.smsbackspringboot.demos.vo.param.AddOrderParam;
import com.example.smsbackspringboot.demos.vo.param.GoodItemParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO订单controller
 */

@RestController
public class orderController {
    @Autowired
    orderService orderService;

    @Autowired
    orderGoodsService orderGoodsService;

    @Autowired
    goodsService goodsService;

    @Autowired
    customerService customerService;


    @ApiOperation(value = "获取订单列表")
    @GetMapping("/order/getOrderList")
    public Result getOrderList(String userName, Long orderId, Integer pageNum, Integer pageSize, Integer status, String startTime, String endTime) {
        System.out.println("111111111111orderList:1111111111111111111111111111111111111111111");
//        List<Long> idList = customerService.getCustomerByName(customerName);
        Result result = orderService.getOrderList(orderId, userName, pageNum, pageSize, status,startTime,endTime);
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
        order.setTotalPrice(orderInfo.getTotalSold());
        System.out.println("payTime"+orderInfo.getPaytime());
//        order.setPaytime(orderInfo.getPaytime());
        Long orderId = orderService.addOrder(order);
        int result = 1;
        if(orderInfo.getCustomerId()!=null){
            //向下取整
            int integral = (int)Math.floor(orderInfo.getTotalSold());
            Customer customer = customerService.getCustomerById(orderInfo.getCustomerId());
            integral = integral+customer.getIntegral();
            customer.setIntegral(integral);
            if(integral >= 100){
                customer.setLevel(1);
            }else if(integral >= 500){
                customer.setLevel(2);
            }
            int update = customerService.updateCustomerInfoById(customer);
        }

        for (GoodItemParam item : orderInfo.getGoodsList()) {
            Long goodsId = item.getGoodId();
            int num = item.getNum();
            Double price = num* item.getPrice();
            //添加订单商品表信息
            int count = orderGoodsService.addOrderGoods(orderId,goodsId,num,price);
            //减少对应商品库存
//            int flag = goodsService.reduceGoodsInventory(goodsId, num);
            result = count * result ;
        }
        if(result == 0){
            Result.error("添加订单失败");
        }
        return Result.success(orderId);
    }

    @ApiOperation(value = "查询每月商品销量")
    @GetMapping("/sales/detail/getGoodsSoldByYear")
    public Result getGoodSoldByYear(String year){
        List<SaleDetailVo> saleDetailVoList = orderService.getGoodSoldByYear(year);
        return Result.success(saleDetailVoList);
    }

    @ApiOperation(value="删除订单信息")
    @DeleteMapping("/order/deleteOrder/{id}")
    public Result deleteOrder(@PathVariable Long id){
        int count = orderService.deleteOrderByid(id);
        if(count>0){
            return Result.success("订单删除成功");
        }else {
            return Result.error("订单删除失败");
        }
    }

    @ApiOperation(value="编辑订单信息")
    @PutMapping("/order/updateOrder")
    public Result updataOrder(@RequestBody Order order){
        Result result = orderService.updataOrder(order);
        return result;
    }

    @ApiOperation(value="现金支付订单")
    @GetMapping("/order/comfirmPayByCash")
    public Result comfirmPayByCash(Long orderId){
        Order orders = orderService.getOrderbyId(orderId);
//                String time= getTime();
        List<Order> orderList = new ArrayList<>();
        orderList.add(orders);

        List<OrderInfoVo>  orderInfoList = orderService.listOrderDetailInfoByOrderId(orderList);
        if (!orderInfoList.isEmpty()) {
            // 获取第一个元素
            OrderInfoVo firstOrderInfo = orderInfoList.get(0);
            for (GoodsItemVo item : firstOrderInfo.getGoodsList()) {
                Long goodsId = item.getGoodId();
                int num = item.getCount();
                //减少对应商品库存
                int flag = goodsService.reduceGoodsInventory(goodsId, num);
            }
        }
        String time = getTime();
        if(orders!=null){
            orders.setOrderStatus(1);
            orders.setPaytime(time);
//                    orders.setPayState(2);
            orderService.updataOrder(orders);
        }
        return Result.success("支付成功");
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getTime() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间
        String formattedDateTime = now.format(formatter);

        // 输出格式化后的时间
        System.out.println(formattedDateTime);
        return  formattedDateTime;
    }
}
