package com.example.smsbackspringboot.demos.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderParam {
    private List<GoodItemParam> goodsList;
    private String customerName;
    private int orderStatus;
    private String detail;
    private String createTime;
    private String payTime;
}

