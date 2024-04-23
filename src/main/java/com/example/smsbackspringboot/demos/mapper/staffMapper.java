package com.example.smsbackspringboot.demos.mapper;

import com.example.smsbackspringboot.demos.entiy.TStaffEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface staffMapper {
     //查询
    @Select("select * from t_staff where staff_id = #{id}")
    TStaffEntity selectById(String id);
    //新增用户信息
    @Select("insert into t_staff ")
    TStaffEntity addStaff(TStaffEntity staff);
    //修改用户信息
}
