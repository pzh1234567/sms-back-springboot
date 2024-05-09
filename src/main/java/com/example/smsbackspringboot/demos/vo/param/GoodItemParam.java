package com.example.smsbackspringboot.demos.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodItemParam {
    private Long goodId;
    private int num;
}
