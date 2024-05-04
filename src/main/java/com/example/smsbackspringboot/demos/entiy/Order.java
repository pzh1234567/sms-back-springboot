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
@TableName("t_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long orderId;
    private String customerName;
    private int orderStatus;
    @TableField(value = "createTime")
    private String createtime;
    @TableField(value = "payTime")
    private String paytime;
    @TableField(value = "updateTime")
    private String updatetime;
    private String detail;

    @TableLogic(value = "0", delval = "1")
    private int deleted;
}
