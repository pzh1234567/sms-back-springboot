package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Bulletin;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.entiy.Order;
import com.example.smsbackspringboot.demos.mapper.bulletinMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bulltinService {
    @Autowired
    bulletinMapper bulletinMapper;

    public Result getBulletinList(String date, String title, String name,Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Bulletin> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Bulletin::getCreateTime);
        wrapper.like(date!=null,Bulletin::getCreateTime,date);
        wrapper.like(title!=null,Bulletin::getTitle,title);
        //查询发布人
        wrapper.like(name!=null,Bulletin::getStaffName,name);
        Page<Bulletin> page = new Page<Bulletin>(pageNum,pageSize);
//        System.out.println("2222222"+Bulletin);
        IPage<Bulletin> bulletinIPage = bulletinMapper.selectPage(page, wrapper);
        List<Bulletin> result = bulletinIPage.getRecords();
        System.out.println(result);
        return Result.success(new PageVo(bulletinIPage.getRecords(),bulletinIPage.getTotal()));
    }

    /**
     * 根据Id删除公告
     */
    public Result deletedBulletinById(Long id){
        int count = bulletinMapper.deleteById(id);
        return Result.success(count);
    };


    /**
     * 编辑公告信息
     * @param bulletin
     * @return
     */
    public Result updateBulletinInfoById(Bulletin bulletin){
        int count = bulletinMapper.updateById(bulletin);
        if(count == 1){
            return Result.success(count);
        }else {
            return Result.success("编辑公告信息失败");
        }
    }

    /**
     * 添加公告
     */
    public Result addBulletinInfo(Bulletin bulletin){
        int count = bulletinMapper.insert(bulletin);
        if(count == 1){
            return Result.success(count);
        }else {
            return Result.success("添加公告信息失败");
        }
    }

    /**
     * 查询每日发布的公告
     * @param date
     * @param status
     * @return
     */
    public Result getBulletinListByDay(String date, Integer status) {
        LambdaQueryWrapper<Bulletin> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(status!=null,Bulletin::getStatus,status);
        wrapper.like(date!=null,Bulletin::getPublishTime,date);
        wrapper.orderByDesc(Bulletin::getPublishTime);
//        查询发布人
        List<Bulletin> bulletinList = bulletinMapper.selectList(wrapper);
        return Result.success(bulletinList);
    }
}
