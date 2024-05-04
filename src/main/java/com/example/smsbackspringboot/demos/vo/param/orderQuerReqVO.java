package com.example.smsbackspringboot.demos.vo.param;

import io.swagger.annotations.ApiModelProperty;

public class orderQuerReqVO extends PageParam {
    @ApiModelProperty(value = "按购买人姓名查询", example = "11")
    private String userName;

    @ApiModelProperty(value = "订单编号", example = "0")
    private Long orderId;
}
