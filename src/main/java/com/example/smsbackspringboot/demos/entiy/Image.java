package com.example.smsbackspringboot.demos.entiy;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
@TableName("t_image")
public class Image {
    @TableId(type = IdType.AUTO)
    private int id;
    private String base64;
}
