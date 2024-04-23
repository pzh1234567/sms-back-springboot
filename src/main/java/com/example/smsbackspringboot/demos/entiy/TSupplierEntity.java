package com.example.smsbackspringboot.demos.entiy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class TSupplierEntity {
    private String supplierId;
    private String supplierName;
    private Timestamp supplierCreateTime;
    private Timestamp supplierUpdateTime;
    private byte deleted;

    @Id
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    public Timestamp getSupplierCreateTime() {
        return supplierCreateTime;
    }

    public void setSupplierCreateTime(Timestamp supplierCreateTime) {
        this.supplierCreateTime = supplierCreateTime;
    }

    @Basic
    public Timestamp getSupplierUpdateTime() {
        return supplierUpdateTime;
    }

    public void setSupplierUpdateTime(Timestamp supplierUpdateTime) {
        this.supplierUpdateTime = supplierUpdateTime;
    }

    @Basic
    public byte getDeleted() {
        return deleted;
    }

    public void setDeleted(byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TSupplierEntity that = (TSupplierEntity) o;
        return deleted == that.deleted &&
                Objects.equals(supplierId, that.supplierId) &&
                Objects.equals(supplierName, that.supplierName) &&
                Objects.equals(supplierCreateTime, that.supplierCreateTime) &&
                Objects.equals(supplierUpdateTime, that.supplierUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, supplierName, supplierCreateTime, supplierUpdateTime, deleted);
    }
}
