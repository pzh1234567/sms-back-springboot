package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("t_good")
public class Goods {
    //指定主键自增
    @TableId(type = IdType.AUTO)
    private Long goodId;

    private String goodName;
    private int goodType;
    private String goodDetail;
    private Float goodCost;
    private Float goodPrice;
    private String goodTotal;
    private String goodInventory;
    private int goodStatus;
    private String goodImg;
    private String stackingTime;
    private String stackingCount;
    private String purchasingparty;
    private String sold;
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

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getGoodType() {
        return goodType;
    }

    public void setGoodType(int goodType) {
        this.goodType = goodType;
    }

    public String getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(String goodDetail) {
        this.goodDetail = goodDetail;
    }

    public Float getGoodCost() {
        return goodCost;
    }

    public void setGoodCost(Float goodCost) {
        this.goodCost = goodCost;
    }

    public Float getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Float goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodTotal() {
        return goodTotal;
    }

    public void setGoodTotal(String goodTotal) {
        this.goodTotal = goodTotal;
    }

    public String getGoodInventory() {
        return goodInventory;
    }

    public void setGoodInventory(String goodInventory) {
        this.goodInventory = goodInventory;
    }

    public int getGoodStatus() {
        return goodStatus;
    }

    public void setGoodStatus(int goodStatus) {
        this.goodStatus = goodStatus;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public String getStackingTime() {
        return stackingTime;
    }

    public void setStackingTime(String stackingTime) {
        this.stackingTime = stackingTime;
    }

    public String getStackingCount() {
        return stackingCount;
    }

    public void setStackingCount(String stackingCount) {
        this.stackingCount = stackingCount;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getGoodCreatetime() {
        return goodCreatetime;
    }

    public void setGoodCreatetime(String goodCreatetime) {
        this.goodCreatetime = goodCreatetime;
    }

    public String getGoodUpdatetime() {
        return goodUpdatetime;
    }

    public void setGoodUpdatetime(String goodUpdatetime) {
        this.goodUpdatetime = goodUpdatetime;
    }

    public int getGoodDeleted() {
        return goodDeleted;
    }

    public void setGoodDeleted(int goodDeleted) {
        this.goodDeleted = goodDeleted;
    }

    public String getPurchasingparty() {
        return purchasingparty;
    }

    public void setPurchasingparty(String purchasingparty) {
        this.purchasingparty = purchasingparty;
    }


}