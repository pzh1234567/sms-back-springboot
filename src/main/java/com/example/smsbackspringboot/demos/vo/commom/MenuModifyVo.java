package com.example.smsbackspringboot.demos.vo.commom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModifyVo {
    private List<MenuLabelVo> menus;
    private List<String> checkedKeys;
}
