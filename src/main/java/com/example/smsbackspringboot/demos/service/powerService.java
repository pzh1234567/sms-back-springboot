package com.example.smsbackspringboot.demos.service;

import com.example.smsbackspringboot.demos.entiy.Power;
import com.example.smsbackspringboot.demos.mapper.powerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class powerService {
    @Autowired
    com.example.smsbackspringboot.demos.mapper.powerMapper powerMapper;

    /**
     * 查询所有权限信息
     * @return
     */
    public List<Power> getPowerList(){
        List<Power> powerList = powerMapper.selectList(null);
        return powerList;
    }
}
