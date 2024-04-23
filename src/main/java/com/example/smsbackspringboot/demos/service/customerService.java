package com.example.smsbackspringboot.demos.service;

import com.example.smsbackspringboot.demos.entiy.Customer;
import com.example.smsbackspringboot.demos.exception.CustomException;
import com.example.smsbackspringboot.demos.mapper.customerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class customerService {


    @Resource
    customerMapper customerMapper;

    /**
     * 功能：新增用户
     **/
    public void addCustomer(Customer customer) {
        System.out.println("1111111");
        Customer dbCustomer = customerMapper.selectById(customer.getId());
        if (dbCustomer != null) {
            throw new CustomException("该用户已经存在");
        } else {
            customerMapper.addCustomer(customer);
        }
    }
    /**
     * 功能：更新用户信息
     **/
    public void updateCustomer(Customer customer){
        customerMapper.updateById(customer);
    }

    /**
     *  功能：删除管理员信息
     **/
    public void deleteCustomer(String id){
        customerMapper.deleteById(id);
    }

    /**
     *  功能：管理员列表查询
     **/
    public List<Customer> getCustomer(){
        List<Customer> customerList = customerMapper.getAll();
        return customerList;
    }
}
