package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.*;
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

    /**
     * 根据角色id获取用户数据
     * @param roleId
     * @return
     */
    public List<Staff> getStaffListbyRoleId(Long roleId){
        LambdaQueryWrapper<StaffRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(roleId!=null,StaffRole::getRoleId,roleId);
        List<StaffRole> staffRoleList = staffRoleMapper.selectList(wrapper);
        List<Staff> staffList = getStaffListbyId(staffRoleList);
        return staffList;
    }

    /**
     * 根据用户id获取角色Id
     * @param staffId
     * @return
     */
    public StaffRole getStaffRoleByStaffId(Long staffId){
        LambdaQueryWrapper<StaffRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(staffId!=null,StaffRole::getStaffId,staffId);
        StaffRole staffRole = staffRoleMapper.selectOne(wrapper);
        return staffRole;
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

    /**
     * 新增staffRoleId 数据
     * @param staffId
     * @param roleId
     * @return
     */
    public int addStaffRole(Long staffId,Long roleId){
        StaffRole staffRole = new StaffRole();
        staffRole.setRoleId(roleId);
        staffRole.setStaffId(staffId);
        int count = staffRoleMapper.insert(staffRole);
        return count;
    }

    /**
     * 删除角色下用户
     * @param roleId
     * @param staffId
     * @return
     */
    public int deleteRoleStaff(Long roleId,Long staffId){
        LambdaQueryWrapper<StaffRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(roleId!=null,StaffRole::getRoleId,roleId);
        wrapper.eq(staffId!=null,StaffRole::getStaffId,staffId);
        int flag = staffRoleMapper.delete(wrapper);
        return flag;
    }

}
