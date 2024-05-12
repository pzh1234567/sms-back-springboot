package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@TableName("t_menu")
@Accessors(chain = true)
public class Menu {

    private int menuId;
    private String menuName;
    private int menuLevel;
    private int parentId;
    private String path;
}
