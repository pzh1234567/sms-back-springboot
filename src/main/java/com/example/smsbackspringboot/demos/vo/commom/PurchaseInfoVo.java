package com.example.smsbackspringboot.demos.vo.commom;

import com.example.smsbackspringboot.demos.entiy.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoVo extends Purchase {
    private String supplierName;
    List<GoodsItemVo> goodsList;
}
