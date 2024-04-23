package com.example.smsbackspringboot.demos.entiy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class TPowerEntity {
    private int powerId;
    private String powerName;
    private String powerDetail;
    private Timestamp powerCreateTime;
    private Timestamp powerUpdateTime;
    private byte powerDeleted;

    @Id
    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    @Basic
    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    @Basic
    public String getPowerDetail() {
        return powerDetail;
    }

    public void setPowerDetail(String powerDetail) {
        this.powerDetail = powerDetail;
    }

    @Basic
    public Timestamp getPowerCreateTime() {
        return powerCreateTime;
    }

    public void setPowerCreateTime(Timestamp powerCreateTime) {
        this.powerCreateTime = powerCreateTime;
    }

    @Basic
    public Timestamp getPowerUpdateTime() {
        return powerUpdateTime;
    }

    public void setPowerUpdateTime(Timestamp powerUpdateTime) {
        this.powerUpdateTime = powerUpdateTime;
    }

    @Basic
    public byte getPowerDeleted() {
        return powerDeleted;
    }

    public void setPowerDeleted(byte powerDeleted) {
        this.powerDeleted = powerDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPowerEntity that = (TPowerEntity) o;
        return powerId == that.powerId &&
                powerDeleted == that.powerDeleted &&
                Objects.equals(powerName, that.powerName) &&
                Objects.equals(powerDetail, that.powerDetail) &&
                Objects.equals(powerCreateTime, that.powerCreateTime) &&
                Objects.equals(powerUpdateTime, that.powerUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(powerId, powerName, powerDetail, powerCreateTime, powerUpdateTime, powerDeleted);
    }
}
