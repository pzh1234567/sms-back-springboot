package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Bulletin;
import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.mapper.bulletinMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bulltinService {
    @Autowired
    bulletinMapper bulletinMapper;

    public Result getBulletinList(String date, Integer title, String name,Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Bulletin> wrapper=new LambdaQueryWrapper<>();
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
}
