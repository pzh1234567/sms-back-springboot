package com.example.smsbackspringboot.demos.vo.commom;

import com.example.smsbackspringboot.demos.entiy.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsItemVo extends Goods {
    // 商品数量
    private Integer count;
}
