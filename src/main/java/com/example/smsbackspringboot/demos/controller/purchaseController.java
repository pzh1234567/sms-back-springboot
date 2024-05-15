package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Purchase;
import com.example.smsbackspringboot.demos.service.purchaseService;
import com.example.smsbackspringboot.demos.vo.commom.PurchaseInfoVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class purchaseController {
    @Autowired
    com.example.smsbackspringboot.demos.service.purchaseService purchaseService;

    @ApiOperation(value = "获取供应单列表")
    @GetMapping("/purchase/getPurchaseList")
    public Result getPurchaseList(String name, Integer pageNum, Integer pageSize) {
        Result result = purchaseService.getPurchaseList(name, pageNum, pageSize);
        return result;
    }

    @ApiOperation(value = "进货")
    @PostMapping("/purchase/addPurchase")
    public Result addPurchase(@RequestBody PurchaseInfoVo purchaseInfoVo) {
        int count = purchaseService.addPurchase(purchaseInfoVo);
        if (count > 0) {
            return Result.success("进货成功");
        } else {
            return Result.error("进货失败");
        }
    }

    @ApiOperation(value = "备注")
    @PutMapping("/purchase/remarkPurchase")
    public Result editPurchase(@RequestBody Purchase purchase) {
        System.out.println(purchase);
        int count = purchaseService.editPurchase(purchase);
        if (count > 0) {
            return Result.success("编辑成功");
        } else {
            return Result.error("编辑失败");
        }
    }

    @ApiOperation(value = "删除供应单")
    @DeleteMapping("/purchase/deletePurchase/{id}")
    public Result deletePurchase(@PathVariable Long id) {
        int count = purchaseService.deletePurchase(id);
        if (count > 0) {
            return Result.success("删除供应单成功");
        } else {
            return Result.error("删除供应单失败");
        }
    }
}
