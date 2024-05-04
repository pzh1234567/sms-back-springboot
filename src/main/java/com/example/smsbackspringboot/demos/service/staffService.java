package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.exception.CustomException;
import com.example.smsbackspringboot.demos.mapper.staffMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class staffService {
    @Resource
    staffMapper staffMapper;

    /**
     * 功能：查询员工列表
     **/
    public Result getStaffList(String name, Integer gender, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Staff> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Staff::getName,name);
        wrapper.eq(gender!=null,Staff::getGender,gender);
//        wrapper.like(goodName!=null,Staff::getGoodName,goodName);
        Page<Staff> page = new Page<Staff>(pageNum,pageSize);
//        System.out.println("2222222"+goodType);
        IPage<Staff> staffIPage = staffMapper.selectPage(page, wrapper);
        List<Staff> result = staffIPage.getRecords();
        System.out.println(result);
        return Result.success(new PageVo(staffIPage.getRecords(),staffIPage.getTotal()));
    }

    /**
     * 根据Id删除员工
     */
    public Result deletedStaffById(Long id){
        int count = staffMapper.deleteById(id);
        return Result.success(count);
    };

    /**
     * 根据Id编辑员工
     */
    public Result updateStaffInfoById(Staff staff){
        int count = staffMapper.updateById(staff);
        return Result.success(count);
    }

    /**
     * 添加员工
     */
    public Result addStaffInfo(Staff staff){
        int count = staffMapper.insert(staff);
        return Result.success(count);
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    public Staff getStaffInfoById(Long id){
        Staff staff = staffMapper.selectById(id);
        return staff;
    }
}
