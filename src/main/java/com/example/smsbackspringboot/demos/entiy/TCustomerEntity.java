package com.example.smsbackspringboot.demos.entiy;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class TCustomerEntity {
    private String customerId;
    private String customerName;
    private byte customerGender;
    private Date customerBirth;
    private byte memberStatus;
    private String integral;
    private String customerPhone;
    private Timestamp customerCreateTime;
    private Timestamp customerUpdateTime;
    private byte customerDeleted;

    @Id
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    public byte getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(byte customerGender) {
        this.customerGender = customerGender;
    }

    @Basic
    public Date getCustomerBirth() {
        return customerBirth;
    }

    public void setCustomerBirth(Date customerBirth) {
        this.customerBirth = customerBirth;
    }

    @Basic
    public byte getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(byte memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Basic
    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    @Basic
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    public Timestamp getCustomerCreateTime() {
        return customerCreateTime;
    }

    public void setCustomerCreateTime(Timestamp customerCreateTime) {
        this.customerCreateTime = customerCreateTime;
    }

    @Basic
    public Timestamp getCustomerUpdateTime() {
        return customerUpdateTime;
    }

    public void setCustomerUpdateTime(Timestamp customerUpdateTime) {
        this.customerUpdateTime = customerUpdateTime;
    }

    @Basic
    public byte getCustomerDeleted() {
        return customerDeleted;
    }

    public void setCustomerDeleted(byte customerDeleted) {
        this.customerDeleted = customerDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCustomerEntity that = (TCustomerEntity) o;
        return customerGender == that.customerGender &&
                memberStatus == that.memberStatus &&
                customerDeleted == that.customerDeleted &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(customerBirth, that.customerBirth) &&
                Objects.equals(integral, that.integral) &&
                Objects.equals(customerPhone, that.customerPhone) &&
                Objects.equals(customerCreateTime, that.customerCreateTime) &&
                Objects.equals(customerUpdateTime, that.customerUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerGender, customerBirth, memberStatus, integral, customerPhone, customerCreateTime, customerUpdateTime, customerDeleted);
    }

    @Override
    public String toString() {
        return "TCustomerEntity{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerGender=" + customerGender +
                ", customerBirth=" + customerBirth +
                ", memberStatus=" + memberStatus +
                ", integral='" + integral + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerCreateTime=" + customerCreateTime +
                ", customerUpdateTime=" + customerUpdateTime +
                ", customerDeleted=" + customerDeleted +
                '}';
    }
}
