package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Role;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.entiy.StaffRole;
import com.example.smsbackspringboot.demos.exception.CustomException;
import com.example.smsbackspringboot.demos.mapper.roleMapper;
import com.example.smsbackspringboot.demos.mapper.staffMapper;
import com.example.smsbackspringboot.demos.mapper.staffRoleMapper;
import com.example.smsbackspringboot.demos.util.BeanCopyUtils;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import com.example.smsbackspringboot.demos.vo.param.AddStaffParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class staffService {
    @Resource
    staffMapper staffMapper;

    @Resource
    staffRoleMapper staffRoleMapper;

    /**
     * 查询员工信息
     * @param roleId
     * @param name
     * @param gender
     * @param pageNum
     * @param pageSize
     * @return
     */

    public Result getStaffList(Long roleId, String name, Integer gender, Integer pageNum, Integer pageSize){
        List<Long> staffIdList = new ArrayList<>();
        LambdaQueryWrapper<Staff> staffWrapper=new LambdaQueryWrapper<>();
        if(roleId !=null){
            LambdaQueryWrapper<StaffRole> staffRoleWrapper=new LambdaQueryWrapper<>();
            staffRoleWrapper.eq(roleId!=null,StaffRole::getRoleId, roleId);
            List<StaffRole> staffRoleList = staffRoleMapper.selectList(staffRoleWrapper);
            //根据roleID获取该角色下的员工Id
            for (StaffRole staffRole: staffRoleList){
                staffIdList.add(staffRole.getStaffId());
            }
            staffWrapper.in(staffIdList.size()!=0,Staff::getId,staffIdList);
        }
        staffWrapper.like(name!=null,Staff::getName,name);
        staffWrapper.eq(gender!=null,Staff::getGender,gender);
        Page<Staff> page = new Page<Staff>(pageNum,pageSize);
//        System.out.println("2222222"+goodType);
        IPage<Staff> staffIPage = staffMapper.selectPage(page, staffWrapper);
        List<Staff> result = staffIPage.getRecords();
        System.out.println(result);
        List<AddStaffParam> addStaffParamsList = getStaffInfoList(result);
        return Result.success(new PageVo(addStaffParamsList,staffIPage.getTotal()));
    }


    /**
     * 根据staff信息获取对应的角色名称
     * @param staffList
     * @return
     */
    public List<AddStaffParam> getStaffInfoList(List<Staff>  staffList){
        List<AddStaffParam> addStaffParamsList = new ArrayList<>();
        for (Staff staff: staffList){
//            AddStaffParam addStaffParam = new AddStaffParam();
            Long staffId = staff.getId();
            LambdaQueryWrapper<StaffRole> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(staffId!=null, StaffRole::getStaffId,staffId);
            StaffRole staffRole = staffRoleMapper.selectOne(wrapper);
            AddStaffParam addStaffParam = BeanCopyUtils.copyBean(staff,AddStaffParam.class);
            if(staffRole!=null){
                Long roleId = staffRole.getRoleId();
                addStaffParam.setRoleId(roleId);
            }
            addStaffParamsList.add(addStaffParam);
        }
        return addStaffParamsList;
    }


    /**
     * 根据Id删除员工
     */
    public int deletedStaffById(Long id){
        System.out.println("id"+id);
        int count = staffMapper.deleteById(id);
        int flag = deletedStaffRoleByStaffId(id);
        return count;
    };

    public int deletedStaffRoleByStaffId(Long staffId){
        LambdaQueryWrapper<StaffRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(staffId!=null,StaffRole::getStaffId,staffId);
        System.out.println(wrapper);
        int flag = staffRoleMapper.delete(wrapper);
        return flag;
    }

    /**
     * 根据Id编辑员工
     */
    public int updateStaffInfoById(AddStaffParam addStaffParam){
        Staff staff = BeanCopyUtils.copyBean(addStaffParam,Staff.class);
        int count = staffMapper.updateById(staff);
        int flag = updateStaffRoleByStaffId(addStaffParam.getId(),addStaffParam.getRoleId());
        System.out.println(count);
        return flag*count;
    }

    /**
     * 编辑staffRole数据
     * @param staffId
     * @param roleId
     * @return
     */
    public int updateStaffRoleByStaffId(Long staffId, Long roleId){
        System.out.println(staffId);
        LambdaQueryWrapper<StaffRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(staffId!=null,StaffRole::getStaffId,staffId);
        StaffRole staffRole = staffRoleMapper.selectOne(wrapper);
        int flag = 1;
        if (staffRole != null && roleId != null) {
            staffRole.setRoleId(roleId);
            flag = staffRoleMapper.updateById(staffRole);
        }
        if(staffRole == null && roleId != null){
            flag = addStaffRole(staffId,roleId);
        }
        return flag;
    }

    /**
     * 添加员工
     * @param addStaffParam
     * @return
     */
    public int addStaffInfo(AddStaffParam addStaffParam){
        LambdaQueryWrapper<Staff> staffWrapper=new LambdaQueryWrapper<>();
        staffWrapper.eq(addStaffParam.getAccount()!=null,Staff::getAccount,addStaffParam.getAccount());
        Staff checked = staffMapper.selectOne(staffWrapper);
        if(checked !=null){
            return -1;
        }
        Staff staff = BeanCopyUtils.copyBean(addStaffParam,Staff.class);
        int count = staffMapper.insert(staff);
        System.out.println(staff.getId());
        System.out.println(addStaffParam.getRoleId());
        int flag = 1;
        if(addStaffParam.getRoleId()!= null){
            flag = addStaffRole(staff.getId(), addStaffParam.getRoleId());
        }
        return flag*count;
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
        int flag = staffRoleMapper.insert(staffRole);
        return flag;
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

    /**
     * 根据账号查询用户信息
     * @param account
     * @return
     */
    public Staff getStaffInfoByAccount(String account){
        LambdaQueryWrapper<Staff> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(account!=null,Staff::getAccount, account);
        Staff staff = staffMapper.selectOne(wrapper);
        return staff;
    }


}
