package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.*;

import javax.persistence.Entity;

@Entity
@TableName("t_customer")
public class Customer {
    //指定主键自增
    @TableId(type = IdType.AUTO)
    private  Long customerId;
    private String customerName;
    private int customerGender;
    private String customerBirth;
    private int level;
    private int integral;
    private String customerPhone;

    @TableField(value = "createTime")
    private String createtime;

    @TableField(value = "updateTime")
    private String updatetime;

    @TableLogic(value = "0", delval = "1")
    private int deleted;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(int customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerBirth() {
        return customerBirth;
    }

    public void setCustomerBirth(String customerBirth) {
        this.customerBirth = customerBirth;
    }


    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerGender=" + customerGender +
                ", customerBirth='" + customerBirth + '\'' +
                ", level=" + level +
                ", integral=" + integral +
                ", customerPhone='" + customerPhone + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
