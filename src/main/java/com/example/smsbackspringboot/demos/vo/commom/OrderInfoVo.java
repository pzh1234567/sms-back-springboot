package com.example.smsbackspringboot.demos.vo.commom;

import com.example.smsbackspringboot.demos.entiy.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoVo extends Order {
    private List<GoodsItemVo> goodsList;
}
