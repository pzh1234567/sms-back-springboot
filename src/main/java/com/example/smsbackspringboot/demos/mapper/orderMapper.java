package com.example.smsbackspringboot.demos.mapper;

import com.example.smsbackspringboot.demos.entiy.Order;
import org.apache.ibatis.annotations.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface orderMapper extends BaseMapper<Order> {
}
