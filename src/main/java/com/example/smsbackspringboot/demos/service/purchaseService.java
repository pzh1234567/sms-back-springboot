package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Purchase;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.mapper.purchaseMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class purchaseService {
    @Autowired
    purchaseMapper purchaseMapper;
    /**
     * 功能：查询供应单列表
     **/
    public Result getPurchaseList(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Purchase> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Purchase::getSupplierName,name);
//        wrapper.like(goodName!=null,Staff::getGoodName,goodName);
        Page<Purchase> page = new Page<Purchase>(pageNum,pageSize);
//        System.out.println("2222222"+goodType);
        IPage<Purchase> purchaseIPage = purchaseMapper.selectPage(page, wrapper);
        List<Purchase> result = purchaseIPage.getRecords();
        System.out.println(result);
        return Result.success(new PageVo(purchaseIPage.getRecords(),purchaseIPage.getTotal()));
    }
}
