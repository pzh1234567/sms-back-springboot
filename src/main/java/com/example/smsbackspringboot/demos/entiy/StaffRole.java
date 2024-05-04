package com.example.smsbackspringboot.demos.entiy;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_staff_role")
public class StaffRole {
    private Integer id;
    //一个用户对应一个岗位；
    private Long staffId;
    private Long roleId;
    @TableLogic(value = "0", delval = "1")
    private int deleted;
}
