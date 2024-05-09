package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_staff")
public class Staff {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String account;
    private String password;
    private Integer gender;
    private String birth;
    private String phone;
    private String idnumber;
    @TableField(value = "CreditcardNumber")
    private String CreditcardNumber;
    private Double salary;
    @TableField(value = "entryTime")
    private String entryTime;
    private String img;
    private Integer depart;
    @TableField(value = "departTime")
    private String departTime;
    private Integer isfreeze;
    @TableField(value = "createTime")
    private String createTime;
    @TableField(value = "updateTime")
    private String updateTime;

    @TableLogic(value = "0", delval = "1")
    private int deleted;

}
