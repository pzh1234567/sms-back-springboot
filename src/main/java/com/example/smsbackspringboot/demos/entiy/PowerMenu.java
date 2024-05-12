package com.example.smsbackspringboot.demos.entiy;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_power_menu")
public class PowerMenu {
    private int id;
    private int poweId;
    private int menuId;
}
