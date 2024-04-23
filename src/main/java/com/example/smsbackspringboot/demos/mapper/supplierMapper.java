package com.example.smsbackspringboot.demos.mapper;

import com.example.smsbackspringboot.demos.entiy.TSupplierEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface supplierMapper {
    //查询
    @Select("select * from t_supplier where supplier_id = #{id}")
    TSupplierEntity selectById(String id);
    //新增用户信息
    @Select("insert into t_supplier ")
    TSupplierEntity addSupplier(TSupplierEntity supplier);
    //修改用户信息
}
