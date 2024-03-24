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
    @Insert("insert into `t_customer` (customer_id,password,username,role,phone,email) "+"values(#{account},#{password},#{username},#{role},#{phone},#{email})")
    void addCustomer(Customer customer);
    //    修改用户信息
    @Update("update m_admin set account = #{account}, password = #{password}, username = #{username}, role = #{role}, phone = #{phone}, email = #{email} where id = #{id}")
    void updateById(Customer customer);
    //    删除用户信息
    @Delete("delete from m_admin where id = #{id}")
    void deleteById(Integer id);
    //    获取用户列表信息
    @Select("select * from m_admin where account like concat('%', #{account}, '%') and username like concat('%', #{name}, '%') order by id desc ")
    List<Customer> selectAll(Customer customer);
    @Select("select * from m_admin")
    List<Customer> getAll();
}
