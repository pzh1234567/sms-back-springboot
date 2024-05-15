package com.example.smsbackspringboot.demos.service;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.OrderGoods;
import com.example.smsbackspringboot.demos.mapper.orderGoodsMapper;
import com.example.smsbackspringboot.demos.mapper.orderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class orderGoodsService {
    @Autowired
    orderGoodsMapper orderGoodsMapper;

    /**
     * 添加
     * @param orderId
     * @param goodsId
     * @param goodsCount
     * @return
     */
    public int addOrderGoods(Long orderId,Long goodsId, int goodsCount,Double price){
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setGoodId(goodsId);
        orderGoods.setGoodCount(goodsCount);
        orderGoods.setOrderId(orderId);
        orderGoods.setGoodPrice(price);
        int count = orderGoodsMapper.insert(orderGoods);
        return count;
    }
}
