package com.example.smsbackspringboot.demos.service;

import com.example.smsbackspringboot.demos.entiy.TStaffEntity;
import com.example.smsbackspringboot.demos.exception.CustomException;
import com.example.smsbackspringboot.demos.mapper.staffMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class staffService {
    @Resource
    private com.example.smsbackspringboot.demos.mapper.staffMapper staffMapper;
    /**
     * 功能：新增员工
     **/
    public void addStaff(TStaffEntity staff) {
        System.out.println("1111111");
        TStaffEntity dbStaff = staffMapper.selectById(staff.getStaffId());
        if (dbStaff != null) {
            throw new CustomException("该用户已经存在");
        } else {
            staffMapper.addStaff(staff);
        }
    }

}
