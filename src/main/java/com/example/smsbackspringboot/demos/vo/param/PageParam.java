package com.example.smsbackspringboot.demos.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;


/**
 * @description: TODO MyBatis分页参数
 * @author: ee
 * @date: 2024/4/8
 */
@ApiModel("分页参数")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam implements Serializable {
	private static final Integer PAGE_NO = 1;
	private static final Integer PAGE_SIZE = 10;

	@ApiModelProperty(value = "页码，从 1 开始", required = true, example = "1")
	@NotNull(message = "页码不能为空")
	@Min(value = 1, message = "页码最小值为 1")
	private Integer pageNo = PAGE_NO;

	@ApiModelProperty(value = "每页条数，最大值为 100", required = true, example = "10")
	@NotNull(message = "每页条数不能为空")
	@Min(value = 1, message = "每页条数最小值为 1")
	@Max(value = 100, message = "每页条数最大值为 100")
	private Integer pageSize = PAGE_SIZE;
}
