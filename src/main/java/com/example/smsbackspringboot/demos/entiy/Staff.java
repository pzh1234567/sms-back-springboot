package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.*;

import javax.persistence.Entity;

@Entity
@TableName("t_staff")
public class Staff {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String account;
    private String password;
    private Integer gender;
    private String birth;
    private String phone;
    private String idnumber;
    @TableField(value = "CreditcardNumber")
    private String CreditcardNumber;
    private Double salary;
    @TableField(value = "entryTime")
    private String entryTime;
    private String img;
    private Integer depart;
    @TableField(value = "departTime")
    private String departTime;
    private Integer isfreeze;
    @TableField(value = "createTime")
    private String createTime;
    @TableField(value = "updateTime")
    private String updateTime;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getCreditcardNumber() {
        return CreditcardNumber;
    }

    public void setCreditcardNumber(String creditcardNumber) {
        CreditcardNumber = creditcardNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getDepart() {
        return depart;
    }

    public void setDepart(Integer depart) {
        this.depart = depart;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public Integer getIsfreeze() {
        return isfreeze;
    }

    public void setIsfreeze(Integer isfreeze) {
        this.isfreeze = isfreeze;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", birth='" + birth + '\'' +
                ", phone='" + phone + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", CreditcardNumber='" + CreditcardNumber + '\'' +
                ", salary=" + salary +
                ", entryTime='" + entryTime + '\'' +
                ", img='" + img + '\'' +
                ", depart=" + depart +
                ", departTime='" + departTime + '\'' +
                ", isfreeze=" + isfreeze +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
