package com.example.smsbackspringboot.demos.entiy;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_purchase")
public class Purchase {
    private Long id;
    private String supplierName;
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
