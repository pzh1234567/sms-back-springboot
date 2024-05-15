package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@TableName("t_good")
public class Goods {
    //指定主键自增
    @TableId(type = IdType.AUTO)
    private Long goodId;

    private Long supplierId;
    private String goodName;
    private int goodType;
    private String goodDetail;
    private Double goodCost;
    private Double goodPrice;
    private int goodTotal;//总量
    private int goodInventory; //库存
    private int goodStatus;
    @TableField(value = "img")
    private String goodImg;
    private String stackingTime;
    private int stackingCount;
//    private String purchasingparty;
    private int sold;
    private String goodCreatetime;
    private String goodUpdatetime;

    @TableLogic(value = "0", delval = "1")
    private int goodDeleted;


}