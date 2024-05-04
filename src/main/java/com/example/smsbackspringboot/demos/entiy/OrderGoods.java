package com.example.smsbackspringboot.demos.entiy;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Entity;

@Entity
@TableName("t_order_good")
public class OrderGoods {
    private Long id;
    private Long OrderId;
    private Long goodId;
    private Integer goodCount;
    private Double actualPayment;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(Double actualPayment) {
        this.actualPayment = actualPayment;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "id=" + id +
                ", OrderId=" + OrderId +
                ", goodId=" + goodId +
                ", goodCount=" + goodCount +
                ", actualPayment=" + actualPayment +
                ", deleted=" + deleted +
                '}';
    }
}
