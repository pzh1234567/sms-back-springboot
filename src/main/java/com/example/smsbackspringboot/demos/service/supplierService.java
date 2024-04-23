package com.example.smsbackspringboot.demos.service;

import com.example.smsbackspringboot.demos.entiy.TSupplierEntity;
import com.example.smsbackspringboot.demos.exception.CustomException;
import com.example.smsbackspringboot.demos.mapper.supplierMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class supplierService {
    @Resource
    private com.example.smsbackspringboot.demos.mapper.supplierMapper supplierMapper;
    /**
     * 功能：新增员工
     **/
    public void addStaff(TSupplierEntity supplier) {
        System.out.println("1111111");
        TSupplierEntity dbSupplier = supplierMapper.selectById(supplier.getSupplierId());
        if (dbSupplier != null) {
            throw new CustomException("该用户已经存在");
        } else {
            supplierMapper.addSupplier(supplier);
        }
    }

}
