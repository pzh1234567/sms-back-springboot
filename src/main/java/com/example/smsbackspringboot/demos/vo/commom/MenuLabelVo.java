package com.example.smsbackspringboot.demos.vo.commom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MenuLabelVo {
    private List<MenuLabelVo> children;
    private int menuId;
    private String menuName;
    private int menuLevel;
    private String path;
    private int parentId;
}
