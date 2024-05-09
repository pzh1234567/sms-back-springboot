package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.entiy.Order;
import com.example.smsbackspringboot.demos.entiy.OrderGoods;
import com.example.smsbackspringboot.demos.mapper.goodsMapper;
import com.example.smsbackspringboot.demos.mapper.orderGoodsMapper;
import com.example.smsbackspringboot.demos.mapper.orderMapper;
import com.example.smsbackspringboot.demos.vo.commom.GoodsItemVo;
import com.example.smsbackspringboot.demos.vo.commom.OrderInfoVo;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class orderService {
    @Autowired
    orderMapper orderMapper;

    @Autowired
    orderGoodsMapper orderGoodsMapper;

    @Autowired
    goodsMapper goodsMapper;

    /**
     * 查询所有订单信息
     * @param orderId
     * @param name
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */

    public Result getOrderList(Long orderId,String name, Integer pageNum, Integer pageSize,Integer status) {
        LambdaQueryWrapper<Order> wrapper=new LambdaQueryWrapper<>();
        //根据会员名字查询
        wrapper.like(name!=null,Order::getCustomerName,name);
        //根据订单id查询
        wrapper.like(orderId!=null,Order::getOrderId,orderId);
        //根据状态查询
        wrapper.eq(status!=null,Order::getOrderStatus,status);
        //分页操作
        Page<Order> page = new Page<Order>(pageNum,pageSize);
        IPage<Order> orderIPage = orderMapper.selectPage(page, wrapper);
        List<Order> result = orderIPage.getRecords();
        System.out.println(result);
        //根据订单信息获取商品信息
        List<OrderInfoVo> orderInfoList = listOrderDetailInfoByOrderId(result);
        return Result.success(new PageVo(orderInfoList,orderIPage.getTotal()));
    }

    /**
     * 根据id条件获取商品信息
     * @param orderList
     * @return
     */
    public List<OrderInfoVo> listOrderDetailInfoByOrderId(List<Order> orderList) {
        List<OrderInfoVo> orderInfoList = new ArrayList<>();
        for (Order order : orderList) {
            //获取查询到列表中的orderId
            Long id = order.getOrderId();
            // 通过orderid获取OrderGoods数据
            LambdaQueryWrapper<OrderGoods> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(id!=null,OrderGoods::getOrderId,id);
            List<OrderGoods> orderGoodsList = orderGoodsMapper.selectList(wrapper);
            List<GoodsItemVo> goodsList = getGoodsItem(orderGoodsList);
            OrderInfoVo orderInfo = new OrderInfoVo();
            orderInfo.setGoodsList(goodsList);
            orderInfo.setOrderId(id);
            orderInfo.setCustomerName(order.getCustomerName());
            orderInfo.setOrderStatus(order.getOrderStatus());
            orderInfo.setCreatetime(order.getCreatetime());
            orderInfo.setPaytime(order.getPaytime());
            orderInfo.setUpdatetime(order.getUpdatetime());
            orderInfo.setDetail(order.getDetail());
            orderInfoList.add(orderInfo);
        }
        return orderInfoList;
    }

    /**
     * 获取GoodsItem
     */
    public List<GoodsItemVo> getGoodsItem(List<OrderGoods> orderGoodsList){
        List<GoodsItemVo> goodsList= new ArrayList<>();
        if(orderGoodsList != null){
            for (OrderGoods orderGoods : orderGoodsList) {
                Long goodId = orderGoods.getGoodId();
                Integer count = orderGoods.getGoodCount();
                // 对goodId进行处理
                System.out.println("GoodId: " + goodId);
                Goods goods = getGoodById(goodId);
                GoodsItemVo goodsItem =new GoodsItemVo();
                goodsItem.setCount(count);
                goodsItem.setGoodId(goodId);
                goodsItem.setGoodCost(goods.getGoodCost());
                goodsItem.setGoodName(goods.getGoodName());
                goodsItem.setGoodPrice(goods.getGoodPrice());
                goodsItem.setGoodType(goods.getGoodType());
                goodsList.add(goodsItem);
            }
        }else {
            System.out.println("找不到订单商品");
        }
        return goodsList;
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
     * 添加订单
     * @param order
     * @return
     */
    public Long addOrder(Order order){
        int count = orderMapper.insert(order);
        return order.getOrderId();
    }
}
