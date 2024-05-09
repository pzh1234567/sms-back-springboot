package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Supplier;
import com.example.smsbackspringboot.demos.service.supplierService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class supplierController {
    @Autowired
    supplierService supplierService;

    @ApiOperation(value="获取供应商列表")
    @GetMapping("/supplier/getSuppliersList")
    public Result getSupplierList(String name,int pageNum, int pageSize){
        Result result = supplierService.getSupplierList(name,pageNum,pageSize);
        return result;
    }

    @ApiOperation(value="添加供应商")
    @PostMapping("/supplier/addSupplier")
    public Result addSupplier(@RequestBody Supplier supplier){
        int count = supplierService.addSupplier(supplier);
        if(count>0){
            return Result.success("添加成功");
        }else {
            return Result.error("添加失败");
        }
    }

    @ApiOperation(value="编辑供应商")
    @PutMapping("/supplier/editSupplier")
    public Result editSupplier(@RequestBody Supplier supplier){
        int count = supplierService.editSupplier(supplier);
        if(count>0){
            return Result.success("编辑成功");
        }else {
            return Result.error("编辑失败");
        }
    }
}
