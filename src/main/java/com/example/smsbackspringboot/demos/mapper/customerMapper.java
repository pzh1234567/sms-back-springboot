package com.example.smsbackspringboot.demos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smsbackspringboot.demos.entiy.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface customerMapper extends BaseMapper<Customer> {

}
