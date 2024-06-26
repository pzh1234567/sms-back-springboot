package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.service.goodsService;
import com.example.smsbackspringboot.demos.vo.param.goodQueryReqVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO商品controller
 */

@RestController
public class GoodsController {
    @Autowired
    goodsService goodsService;
    /**
     * 新增商品信息
     */

//    @PostMapping("/addGoods")
//    public Result addGoods(@RequestBody TGoodEntity good){
//        goodsService.addGoods(good);
//        return Result.success();
//    }
    /**
     * 获取商品列表
     */
    @ApiOperation(value = "获取商品列表")
    @GetMapping("/good/getAllGoodList")
    public Result getGoods(String goodName,Integer goodType,Integer pageNum,Integer pageSize,Integer status){
        System.out.println("111111111111goodList:1111111111111111111111111111111111111111111");
        Result result = goodsService.getGoodsList(goodName,goodType,pageNum,pageSize,status);
        return result;

    }

//    @ApiOperation(value = "添加商品")
//    @PostMapping("/good/addNewGood")
//    public Result addNewGood(){
//        List<TGood> GoodsList = goodsService.getGoodsList(goodQueryReqVO);
//        return
//    };
    @ApiOperation(value = "通过Id查询商品")
    @GetMapping("/good/getGoodById")
    public Result getGoodById(String id){
        Goods goods = goodsService.getGoodById(id);
        return Result.success(goods);
    }

    @ApiOperation(value = "通过Id删除")
    @DeleteMapping("/good/deleteGoodById/{id}")
    public Result deleteGoodById(@PathVariable("id") Long id){
        Result result = goodsService.deletedGoodById(id);
        return result;
    }

    @ApiOperation(value = "更新商品信息")
    @PutMapping("/good/editGoodItem")
    public Result updateGoodsInfoById(@RequestBody Goods goods) {
        Result result = goodsService.updateGoodsInfoById(goods);
        return result;
    }

    @ApiOperation(value="获取库存小于10的商品")
    @GetMapping("/good/getGoodItembyInventory")
    public Result getGoodsByInventory(){
        Result result = goodsService.getGoodsByInventory();
        return result;
    }

    @ApiOperation(value="获取不同种类商品的销量")
    @GetMapping("/good/getTypeSold")
    public Result getTypeSold(){
//        System.out.println("获取不同种类商品的销量");
        Result result = goodsService.getTypeSold();
        return result;
    }

    @ApiOperation(value="获取销量前三的商品")
    @GetMapping("/good/gethighsellingGood")
    public Result gethighsellingGood(){
        Result result = goodsService.gethighsellingGood();
        return result;
    }
}
