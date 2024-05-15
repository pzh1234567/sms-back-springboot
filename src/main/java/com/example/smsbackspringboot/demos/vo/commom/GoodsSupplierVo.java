package com.example.smsbackspringboot.demos.vo.commom;

import com.example.smsbackspringboot.demos.entiy.Goods;
import com.example.smsbackspringboot.demos.entiy.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSupplierVo extends Goods {
    private String SupplierName;
}
