package com.example.smsbackspringboot.demos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smsbackspringboot.demos.entiy.Image;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface imageMapper extends BaseMapper<Image> {
}
