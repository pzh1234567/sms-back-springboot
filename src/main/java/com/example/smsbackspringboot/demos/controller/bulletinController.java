package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Bulletin;
import com.example.smsbackspringboot.demos.service.bulltinService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO公告controller
 */

@RestController
public class bulletinController {
    @Autowired
    bulltinService bulltinService;

    @ApiOperation(value = "获取公告列表")
    @GetMapping("/bulletin/getuBulletinList")
    public Result getBulletinList(String date, Integer title, String name,Integer pageNum, Integer pageSize){
        Result result = bulltinService.getBulletinList(date, title,name,pageNum,pageSize);
        return result;

    }

    @ApiOperation(value = "删除公告")
    @DeleteMapping("/bulletin/deleteBulletin/{id}")
    public Result deleteBulletinById(@PathVariable("id") Long id){
        Result result = bulltinService.deletedBulletinById(id);
        return result;
    }

    @ApiOperation(value = "编辑公告信息")
    @PutMapping("/bulletin/editBulletin")
    public Result editBulletinById(@RequestBody Bulletin bulletin){
        System.out.println("2222222"+bulletin);
        Result result = bulltinService.updateBulletinInfoById(bulletin);
        return result;
    }

    @ApiOperation(value = "添加公告")
    @PostMapping("/bulletin/addBulletin")
    public Result addBulletinInfo(@RequestBody Bulletin bulletin){
        System.out.println("2222222"+bulletin);
        Result result = bulltinService.addBulletinInfo(bulletin);
        return result;
    }
}
