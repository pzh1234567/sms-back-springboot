package com.example.smsbackspringboot.demos.service;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.MemberRedemption;
import com.example.smsbackspringboot.demos.mapper.memberRedemptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberRedemptionService {
    @Autowired
    memberRedemptionMapper memberRedemptionMapper;

    public Result getMemberRedemptionList(){
        List<MemberRedemption> memberRedemptionList = memberRedemptionMapper.selectList(null);
        return Result.success(memberRedemptionList);
    }
}
