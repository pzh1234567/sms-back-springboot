package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@TableName("t_good")
public class Goods {
    //指定主键自增
    @TableId(type = IdType.AUTO)
    private Long goodId;

    private String goodName;
    private int goodType;
    private String goodDetail;
    private Double goodCost;
    private Double goodPrice;
    private Integer goodTotal;
    private String goodInventory;
    private int goodStatus;
    private String goodImg;
    private String stackingTime;
    private String stackingCount;
    private String purchasingparty;
    private Integer sold;
    private String goodCreatetime;
    private String goodUpdatetime;

    @TableLogic(value = "0", delval = "1")
    private int goodDeleted;

    @Override
    public String toString() {
        return "Goods{" +
                "goodId=" + goodId +
                ", goodName='" + goodName + '\'' +
                ", goodType=" + goodType +
                ", goodDetail='" + goodDetail + '\'' +
                ", goodCost=" + goodCost +
                ", goodPrice=" + goodPrice +
                ", goodTotal='" + goodTotal + '\'' +
                ", goodInventory='" + goodInventory + '\'' +
                ", goodStatus=" + goodStatus +
                ", goodImg='" + goodImg + '\'' +
                ", stackingTime='" + stackingTime + '\'' +
                ", stackingCount='" + stackingCount + '\'' +
                ", purchasingParty='" + purchasingparty + '\'' +
                ", sold='" + sold + '\'' +
                ", goodCreatetime='" + goodCreatetime + '\'' +
                ", goodUpdatetime='" + goodUpdatetime + '\'' +
                ", goodDeleted=" + goodDeleted +
                '}';
    }

}