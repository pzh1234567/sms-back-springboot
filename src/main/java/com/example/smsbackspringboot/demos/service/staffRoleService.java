package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Order;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.entiy.StaffRole;
import com.example.smsbackspringboot.demos.mapper.staffMapper;
import com.example.smsbackspringboot.demos.mapper.staffRoleMapper;
import com.example.smsbackspringboot.demos.vo.commom.OrderInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class staffRoleService {
    @Autowired
    staffRoleMapper staffRoleMapper;

    @Autowired
    staffMapper staffMapper;

    public Result getStaffListbyRoleId(Long roleId){
        LambdaQueryWrapper<StaffRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(roleId!=null,StaffRole::getRoleId,roleId);
        List<StaffRole> staffRoleList = staffRoleMapper.selectList(wrapper);
        List<Staff> staffList = getStaffListbyId(staffRoleList);
        return Result.success(staffList);
    }

    /**
     * 通过staffRole里的staffid获取stafflist
     * @param staffRoleList
     * @return
     */
    public List<Staff> getStaffListbyId(List<StaffRole> staffRoleList){
        List<Staff> staffList = new ArrayList<>();
        for (StaffRole staffRole : staffRoleList) {
            //获取查询到列表中的orderId
            Long staffId = staffRole.getStaffId();
            Staff staff = staffMapper.selectById(staffId);
            staffList.add(staff);
        }
        return staffList;
    }
}
