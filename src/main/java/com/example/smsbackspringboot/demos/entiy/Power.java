package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_power")
public class Power {
    @TableId(value = "power_id",type = IdType.AUTO)
    @TableField(value = "power_id")
    private int id;
    @TableField(value = "power_name")
    private String name;
    @TableField(value = "power_detail")
    private String detail;
    @TableField(value = "power_createTime")
    private String createTime;
    @TableField(value = "power_updateTime")
    private String updateTime;
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "power_deleted")
    private int deleted;
}
