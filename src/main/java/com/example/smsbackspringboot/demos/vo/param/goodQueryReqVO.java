package com.example.smsbackspringboot.demos.vo.param;

import io.swagger.annotations.ApiModelProperty;

public class goodQueryReqVO extends PageParam{
    @ApiModelProperty(value = "按商品名查询", example = "猫粮")
    private String name;

    @ApiModelProperty(value = "商品类别", example = "0")
    private Integer type;

}

