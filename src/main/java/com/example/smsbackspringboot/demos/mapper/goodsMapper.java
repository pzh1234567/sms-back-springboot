package com.example.smsbackspringboot.demos.mapper;

import com.example.smsbackspringboot.demos.entiy.Goods;
import org.apache.ibatis.annotations.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface goodsMapper extends BaseMapper<Goods> {
}