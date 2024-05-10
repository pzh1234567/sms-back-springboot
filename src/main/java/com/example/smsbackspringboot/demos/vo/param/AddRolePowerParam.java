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
public class AddRolePowerParam {
    Long roleId;
    List<Integer> powerIdList;
}
