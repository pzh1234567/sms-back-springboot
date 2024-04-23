package com.example.smsbackspringboot.demos.entiy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class TStaffEntity {
    private String staffId;
    private String staffName;
    private String staffAccount;
    private String staffPassword;
    private byte staffGender;
    private Date staffBirth;
    private String staffPhone;
    private String staffIdnumber;
    private String staffCreditcardnumber;
    private BigDecimal staffSalary;
    private Timestamp staffEntryTime;
    private String staffImg;
    private Byte staffDepart;
    private Timestamp departTime;
    private Byte isfreeze;
    private Timestamp staffCreateTime;
    private Timestamp staffUpdateTime;
    private byte staffDeleted;

    @Id
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    @Basic
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    public String getStaffAccount() {
        return staffAccount;
    }

    public void setStaffAccount(String staffAccount) {
        this.staffAccount = staffAccount;
    }

    @Basic
    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    @Basic
    public byte getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(byte staffGender) {
        this.staffGender = staffGender;
    }

    @Basic
    public Date getStaffBirth() {
        return staffBirth;
    }

    public void setStaffBirth(Date staffBirth) {
        this.staffBirth = staffBirth;
    }

    @Basic
    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    @Basic
    public String getStaffIdnumber() {
        return staffIdnumber;
    }

    public void setStaffIdnumber(String staffIdnumber) {
        this.staffIdnumber = staffIdnumber;
    }

    @Basic
    public String getStaffCreditcardnumber() {
        return staffCreditcardnumber;
    }

    public void setStaffCreditcardnumber(String staffCreditcardnumber) {
        this.staffCreditcardnumber = staffCreditcardnumber;
    }

    @Basic
    public BigDecimal getStaffSalary() {
        return staffSalary;
    }

    public void setStaffSalary(BigDecimal staffSalary) {
        this.staffSalary = staffSalary;
    }

    @Basic
    public Timestamp getStaffEntryTime() {
        return staffEntryTime;
    }

    public void setStaffEntryTime(Timestamp staffEntryTime) {
        this.staffEntryTime = staffEntryTime;
    }

    @Basic
    public String getStaffImg() {
        return staffImg;
    }

    public void setStaffImg(String staffImg) {
        this.staffImg = staffImg;
    }

    @Basic
    public Byte getStaffDepart() {
        return staffDepart;
    }

    public void setStaffDepart(Byte staffDepart) {
        this.staffDepart = staffDepart;
    }

    @Basic
    public Timestamp getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Timestamp departTime) {
        this.departTime = departTime;
    }

    @Basic
    public Byte getIsfreeze() {
        return isfreeze;
    }

    public void setIsfreeze(Byte isfreeze) {
        this.isfreeze = isfreeze;
    }

    @Basic
    public Timestamp getStaffCreateTime() {
        return staffCreateTime;
    }

    public void setStaffCreateTime(Timestamp staffCreateTime) {
        this.staffCreateTime = staffCreateTime;
    }

    @Basic
    public Timestamp getStaffUpdateTime() {
        return staffUpdateTime;
    }

    public void setStaffUpdateTime(Timestamp staffUpdateTime) {
        this.staffUpdateTime = staffUpdateTime;
    }

    @Basic
    public byte getStaffDeleted() {
        return staffDeleted;
    }

    public void setStaffDeleted(byte staffDeleted) {
        this.staffDeleted = staffDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TStaffEntity that = (TStaffEntity) o;
        return staffGender == that.staffGender &&
                staffDeleted == that.staffDeleted &&
                Objects.equals(staffId, that.staffId) &&
                Objects.equals(staffName, that.staffName) &&
                Objects.equals(staffAccount, that.staffAccount) &&
                Objects.equals(staffPassword, that.staffPassword) &&
                Objects.equals(staffBirth, that.staffBirth) &&
                Objects.equals(staffPhone, that.staffPhone) &&
                Objects.equals(staffIdnumber, that.staffIdnumber) &&
                Objects.equals(staffCreditcardnumber, that.staffCreditcardnumber) &&
                Objects.equals(staffSalary, that.staffSalary) &&
                Objects.equals(staffEntryTime, that.staffEntryTime) &&
                Objects.equals(staffImg, that.staffImg) &&
                Objects.equals(staffDepart, that.staffDepart) &&
                Objects.equals(departTime, that.departTime) &&
                Objects.equals(isfreeze, that.isfreeze) &&
                Objects.equals(staffCreateTime, that.staffCreateTime) &&
                Objects.equals(staffUpdateTime, that.staffUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, staffName, staffAccount, staffPassword, staffGender, staffBirth, staffPhone, staffIdnumber, staffCreditcardnumber, staffSalary, staffEntryTime, staffImg, staffDepart, departTime, isfreeze, staffCreateTime, staffUpdateTime, staffDeleted);
    }
}
