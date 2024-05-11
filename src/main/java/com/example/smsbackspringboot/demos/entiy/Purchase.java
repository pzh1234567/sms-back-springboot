package com.example.smsbackspringboot.demos.entiy;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_purchase")
public class Purchase {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long supplierId;
    private int status;
    private String detail;
    @TableField(value = "purchaseTime")
    private String purchaseTime;
    @TableField(value = "createTime")
    private String createTime;
    @TableField(value = "updateTime")
    private String updateTime;
    @TableLogic(value = "0", delval = "1")
    private int deleted;
}
