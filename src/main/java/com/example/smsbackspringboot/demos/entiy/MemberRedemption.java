package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
@TableName("t_member_redemption")
public class MemberRedemption {
    //指定主键自增
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String img;
    private int interge;
    private int memberLevel;
    private int inventory;
    private int total;
    @TableField(value = "createTime")
    private String createtime;

    @TableField(value = "updateTime")
    private String updatetime;

    @TableLogic(value = "0", delval = "1")
    private int deleted;
}
