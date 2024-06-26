package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Bulletin;
import com.example.smsbackspringboot.demos.entiy.Customer;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.exception.CustomException;
import com.example.smsbackspringboot.demos.mapper.customerMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class customerService {


    @Resource
    customerMapper customerMapper;

    /**
     * 功能：根据id查询用户信息
     **/
    public Customer getCustomerById(Long id){
//        LambdaQueryWrapper<Customer> wrapper=new LambdaQueryWrapper<>();
//        wrapper.eq(id!=null,Customer::getCustomerId,id);
        Customer customer = customerMapper.selectById(id);
        System.out.println(customer);
        return customer;
    }

    /**
     * 功能：根据name查询ID列表
     **/
    public List<Long> getCustomerByName(String name){
        LambdaQueryWrapper<Customer> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(name!=null,Customer::getCustomerName,name);
        List<Customer> customer = customerMapper.selectList(wrapper);
        System.out.println(customer);
        List<Long> customerIds = new ArrayList<>();
        for (Customer e : customer) {
            Long customerId = e.getCustomerId();
            customerIds.add(customerId);
        }
        return customerIds;
    }

    /**
     * 功能：查询顾客列表
     **/
    public Result getCustomerList(Integer level, String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Customer> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(level!=null,Customer::getLevel,level);
        wrapper.like(name!=null,Customer::getCustomerName,name);
        Page<Customer> page = new Page<Customer>(pageNum,pageSize);
//        System.out.println("2222222"+Bulletin);
        IPage<Customer> customerIPage = customerMapper.selectPage(page, wrapper);
        List<Customer> result = customerIPage.getRecords();
        System.out.println(result);
        return Result.success(new PageVo(customerIPage.getRecords(),customerIPage.getTotal()));
    }

    /**
     * 功能：更新顾客信息
     **/
    public int updateCustomerInfoById(Customer customer){
        //判断电话号码是否存在
        LambdaQueryWrapper<Customer> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(customer.getCustomerPhone()!=null,Customer::getCustomerPhone,customer.getCustomerPhone());
        wrapper.ne(customer.getCustomerId()!=null,Customer::getCustomerId,customer.getCustomerId());
        Customer check = customerMapper.selectOne(wrapper);
        System.out.println("customerList:"+customer);
        int count = customerMapper.updateById(customer);
        return count;
    }

    /**
     * 功能：新增会员信息
     **/
    public Result addCustomerInfo(Customer customer) {
        //判断电话号码是否存在
        LambdaQueryWrapper<Customer> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(customer.getCustomerPhone()!=null,Customer::getCustomerPhone,customer.getCustomerPhone());
        Customer check = customerMapper.selectOne(wrapper);
//        System.out.println("1111111");
        if(check!=null){
            return Result.error("电话号码已经被注册");
        }
        int count = customerMapper.insert(customer);
        System.out.println(customer.getCustomerId());
        return Result.success(count);
    }

    /**
     * 根据Id删除会员
     */
    public Result deletedCustomerById(Long id){
        int count = customerMapper.deleteById(id);
        return Result.success(count);
    };

    /**
     * 根据会员电话查找会员信息
     * @param phone
     * @return
     */
    public Customer getCustomerByPhone(String phone){
        LambdaQueryWrapper<Customer> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(phone!=null,Customer::getCustomerPhone,phone);
        Customer customer = customerMapper.selectOne(wrapper);
        return customer;
    };


}
