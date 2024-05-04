package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.entiy.Order;
import com.example.smsbackspringboot.demos.entiy.OrderGoods;
import com.example.smsbackspringboot.demos.mapper.goodsMapper;
import com.example.smsbackspringboot.demos.mapper.orderGoodsMapper;
import com.example.smsbackspringboot.demos.mapper.orderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class orderGoodsService {
    @Autowired
    orderGoodsMapper orderGoodsMapper;

    @Autowired
    goodsMapper goodsMapper;

    @Autowired
    orderMapper orderMapper;
    /**
     * 查询订单详情列表
     * @param orderId
     * @return
     */
    public List<OrderGoods> listOrderDetailInfoByOrderId(Long orderId) {
        LambdaQueryWrapper<OrderGoods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(orderId!=null,OrderGoods::getOrderId,orderId);
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectList(wrapper);
        for (OrderGoods orderGoods : orderGoodsList) {
            Long goodId = orderGoods.getGoodId();
            // 对goodId进行处理，如打印输出或其他操作
            System.out.println("GoodId: " + goodId);
        }
        return orderGoodsList;
    }


    /**
     * 根据id条件获取商品信息
     * @param id
     * @return
     */
    public Goods getGoodById(Long id) {
        Goods goods = goodsMapper.selectById(id);
        return goods;
    }

    /**
     * 根据id条件获取订单信息
     * @param id
     * @return
     */
    public Order getOrderById(Long id) {
        Order order = orderMapper.selectById(id);
        return order;
    }

}
