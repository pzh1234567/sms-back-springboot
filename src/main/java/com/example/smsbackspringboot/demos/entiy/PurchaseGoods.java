package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_purchase_good")
public class PurchaseGoods {
    @TableId(type = IdType.AUTO)
    private int id;
    private Long purchaseId;
    private Long goodId;
    private int goodCount;
    private int goodStatus;
    private Double goodCost;
    private int deleted;
}
