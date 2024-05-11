package com.example.smsbackspringboot.demos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smsbackspringboot.demos.entiy.PurchaseGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface purchaseGoodsMapper extends BaseMapper<PurchaseGoods> {
}
