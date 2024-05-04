package com.example.smsbackspringboot.demos.vo.commom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsItemVo {

    // 商品数量
    private Integer count;
    private Long goodId;
    private String goodName;
    private int goodType;
    private String goodDetail;
    //成本
    private Double goodCost;
    //售价
    private Double goodPrice;
}
