package com.example.smsbackspringboot.demos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smsbackspringboot.demos.entiy.Staff;
import org.apache.ibatis.annotations.*;

@Mapper
public interface staffMapper extends BaseMapper<Staff> {
}
