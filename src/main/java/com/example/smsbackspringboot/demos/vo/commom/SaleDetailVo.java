package com.example.smsbackspringboot.demos.vo.commom;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailVo {
    // 存放每月销量
    private Integer sold;
//    List<GoodsItemVo> goodsList;
//    每月利润
    private Double profit;
}

