package com.example.smsbackspringboot.demos.mapper;

import com.example.smsbackspringboot.demos.entiy.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface customerMapper {
    //    查询信息
    @Select("select * from t_customer where customer_id = #{id}")
    Customer selectById(String id);
    //    新增用户信息
    @Insert("insert into `t_customer` (customer_id,customer_name,customer_gender,customer_birth,memberStatus,integral,customer_phone, customer_createTime,customer_updateTime,customer_deleted) "+"values(#{id},#{name},#{sex},#{birth},#{status},#{integral},#{phone},#{createTime},#{updateTime},#{customerDelete})")
    void addCustomer(Customer customer);
    //    修改用户信息
    @Update("update t_customer set customer_name = #{name}, customer_gender = #{sex}, customer_birth = #{birth}, memberStatus = #{status}, integral = #{integral}, customer_phone = #{phone} ,customer_createTime=#{createTime},customer_updateTime=#{updateTime},customer_deleted=#{customerDelete}  where id = #{id}")
    void updateById(Customer customer);
    //    删除用户信息
    @Delete("delete from t_customer where customer_id = #{id}")
    void deleteById(String id);
    //    获取用户列表信息
    @Select("select * from t_customer where customer_id like concat('%', #{id}, '%') and customer_name like concat('%', #{name}, '%') order by id desc ")
    List<Customer> selectAll(Customer customer);
    @Select("select * from t_customer")
    List<Customer> getAll();
}
