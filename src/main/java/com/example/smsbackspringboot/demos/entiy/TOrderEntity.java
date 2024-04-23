package com.example.smsbackspringboot.demos.entiy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class TOrderEntity {
    private String orderId;
    private String customerId;
    private byte orderStatus;
    private Timestamp orderCreateTime;
    private Timestamp orderPayTime;
    private Timestamp orderUpdateTime;
    private byte orderDeleted;

    @Id
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "order_status")
    public byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "order_createTime")
    public Timestamp getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Timestamp orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    @Basic
    @Column(name = "order_payTime")
    public Timestamp getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Timestamp orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    @Basic
    @Column(name = "order_updateTime")
    public Timestamp getOrderUpdateTime() {
        return orderUpdateTime;
    }

    public void setOrderUpdateTime(Timestamp orderUpdateTime) {
        this.orderUpdateTime = orderUpdateTime;
    }

    @Basic
    @Column(name = "order_deleted")
    public byte getOrderDeleted() {
        return orderDeleted;
    }

    public void setOrderDeleted(byte orderDeleted) {
        this.orderDeleted = orderDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TOrderEntity that = (TOrderEntity) o;
        return orderStatus == that.orderStatus &&
                orderDeleted == that.orderDeleted &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(orderCreateTime, that.orderCreateTime) &&
                Objects.equals(orderPayTime, that.orderPayTime) &&
                Objects.equals(orderUpdateTime, that.orderUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, orderStatus, orderCreateTime, orderPayTime, orderUpdateTime, orderDeleted);
    }
}
