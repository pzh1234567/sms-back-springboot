package com.example.smsbackspringboot.demos.entiy;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_bulletin")
public class Bulletin {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String staffName;
    private String content;
    private Integer status;
    @TableField(value = "createTime")
    private String createTime;
    @TableField(value = "updateTime")
    private String updateTime;
    @TableField(value = "publishTime")
    private String publishTime;
    @TableLogic(value = "0", delval = "1")
    private int deleted;
}
