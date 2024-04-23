package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smsbackspringboot.demos.entiy.Menu;
import com.example.smsbackspringboot.demos.mapper.menuMapper;
import com.example.smsbackspringboot.demos.util.BeanCopyUtils;
import com.example.smsbackspringboot.demos.vo.commom.MenuLabelVo;
import com.example.smsbackspringboot.demos.vo.commom.MenuModifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class menuService {

    @Autowired
    menuMapper menuMapper;

    /**
     * 获取所有menu
     * @param
     * @return
     */
    public MenuModifyVo getAllMenu() {
        // 从数据库获取菜单列表
        List<Menu> menuList = menuMapper.selectList(null);
        System.out.println("1111111113"+menuList);
        // 将 Menu 对象列表转换为 MenuLabelVo 对象列表
        List<MenuLabelVo> menus = BeanCopyUtils.copyBeanList(menuList, MenuLabelVo.class);
        System.out.println("1111111112"+menus);
        // 构建树形结构
        List<MenuLabelVo> menuLabelVoList = builderLabelMenuTree(menus, 1);
        System.out.println("111111"+menuLabelVoList);
        // 创建 MenuModifyVo 对象
        MenuModifyVo menuModifyVo = new MenuModifyVo();
        List<String> checkedKeys = new LinkedList<>(); // 假设你有一种方法来获取 checkedKeys
        menuModifyVo.setCheckedKeys(checkedKeys);
        menuModifyVo.setMenus(menuLabelVoList);

        // 返回 MenuModifyVo 对象
        System.out.println("2222222"+menuModifyVo);
        return menuModifyVo;
    }

    private List<MenuLabelVo> builderLabelMenuTree(List<MenuLabelVo> menus, int level) {
        List<MenuLabelVo> menuTree = menus.stream()
                .filter(menu -> Objects.equals(menu.getMenuLevel(), level))
                .map(menu -> menu.setChildren(getMenusChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<MenuLabelVo> getMenusChildren(MenuLabelVo menu, List<MenuLabelVo> menus) {
        List<MenuLabelVo> childrenList = menus.stream()
//                .filter(m -> m.getParentId().equals(menu.getMenuId()))
                .filter(m -> Objects.equals(m.getParentId(), menu.getMenuId()))
                .map(m-> m.setChildren(getMenusChildren(m, menus)))
                .collect(Collectors.toList());
        return childrenList;
    }

}
