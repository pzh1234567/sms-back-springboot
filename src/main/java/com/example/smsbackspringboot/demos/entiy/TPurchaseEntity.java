package com.example.smsbackspringboot.demos.entiy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class TPurchaseEntity {
    private String purchaseId;
    private String supplierId;
    private byte purchaseStatus;
    private Timestamp purchaseTime;
    private Timestamp purchaseCreateTime;
    private Timestamp purchaseUpdateTime;
    private byte deleted;
    private String dedtail;

    @Id
    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Basic
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    public byte getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(byte purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    @Basic
    public Timestamp getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Timestamp purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Basic
    public Timestamp getPurchaseCreateTime() {
        return purchaseCreateTime;
    }

    public void setPurchaseCreateTime(Timestamp purchaseCreateTime) {
        this.purchaseCreateTime = purchaseCreateTime;
    }

    @Basic
    public Timestamp getPurchaseUpdateTime() {
        return purchaseUpdateTime;
    }

    public void setPurchaseUpdateTime(Timestamp purchaseUpdateTime) {
        this.purchaseUpdateTime = purchaseUpdateTime;
    }

    @Basic
    public byte getDeleted() {
        return deleted;
    }

    public void setDeleted(byte deleted) {
        this.deleted = deleted;
    }

    @Basic
    public String getDedtail() {
        return dedtail;
    }

    public void setDedtail(String dedtail) {
        this.dedtail = dedtail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPurchaseEntity that = (TPurchaseEntity) o;
        return purchaseStatus == that.purchaseStatus &&
                deleted == that.deleted &&
                Objects.equals(purchaseId, that.purchaseId) &&
                Objects.equals(supplierId, that.supplierId) &&
                Objects.equals(purchaseTime, that.purchaseTime) &&
                Objects.equals(purchaseCreateTime, that.purchaseCreateTime) &&
                Objects.equals(purchaseUpdateTime, that.purchaseUpdateTime) &&
                Objects.equals(dedtail, that.dedtail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, supplierId, purchaseStatus, purchaseTime, purchaseCreateTime, purchaseUpdateTime, deleted, dedtail);
    }
}
