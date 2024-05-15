package com.example.smsbackspringboot.demos.entiy;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order_good")
public class OrderGoods {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long OrderId;
    private Long goodId;
    private int goodCount;
    private Double goodPrice;
//    private Double actualPayment;
    @TableLogic(value = "0", delval = "1")
    private int deleted;
}

