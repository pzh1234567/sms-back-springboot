package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.entiy.Supplier;
import com.example.smsbackspringboot.demos.exception.CustomException;
import com.example.smsbackspringboot.demos.mapper.supplierMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class supplierService {
    @Resource
    supplierMapper supplierMapper;
    /**
     * 功能：查看供应商列表
     **/
    public Result getSupplierList(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Supplier> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Supplier::getName,name);
//        wrapper.like(goodName!=null,Staff::getGoodName,goodName);
        Page<Supplier> page = new Page<Supplier>(pageNum,pageSize);
//        System.out.println("2222222"+goodType);
        IPage<Supplier> supplierIPage = supplierMapper.selectPage(page, wrapper);
        List<Supplier> result = supplierIPage.getRecords();
        System.out.println(result);
        return Result.success(new PageVo(supplierIPage.getRecords(),supplierIPage.getTotal()));
    }

    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    public int addSupplier(Supplier supplier){
        int count = supplierMapper.insert(supplier);
        return count;
    }

    /**
     * 编辑供应商
     * @param supplier
     * @return
     */
    public int editSupplier(Supplier supplier){
        int count = supplierMapper.updateById(supplier);
        return count;
    }

}
