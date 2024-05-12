package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smsbackspringboot.demos.entiy.*;
import com.example.smsbackspringboot.demos.mapper.menuMapper;
import com.example.smsbackspringboot.demos.mapper.powerMenuMapper;
import com.example.smsbackspringboot.demos.mapper.rolePowerMapper;
import com.example.smsbackspringboot.demos.mapper.staffRoleMapper;
import com.example.smsbackspringboot.demos.util.BeanCopyUtils;
import com.example.smsbackspringboot.demos.vo.commom.MenuLabelVo;
import com.example.smsbackspringboot.demos.vo.commom.MenuModifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class menuService {

    @Autowired
    menuMapper menuMapper;

    @Autowired
    staffRoleMapper staffRoleMapper;

    @Autowired
    rolePowerMapper rolePowerMapper;

    @Autowired
    powerMenuMapper powerMenuMapper;

    public MenuModifyVo getMenu(Long staffId){
        MenuModifyVo menuModifyVo = new MenuModifyVo();
        if(staffId == null){
            return menuModifyVo;
        }
        System.out.println("staffId"+staffId);
        //查询用户对应的角色
        LambdaQueryWrapper<StaffRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(staffId!=null, StaffRole::getStaffId, staffId);
        StaffRole staffRole = staffRoleMapper.selectOne(wrapper);
        Long roleId = staffRole.getRoleId();
         // 将变量声明移到外部
        if (roleId == 1) {
            menuModifyVo = getAllMenu();
        } else {
            menuModifyVo = getMenuByRoleId(roleId);
        }
        return menuModifyVo;
    }


    public MenuModifyVo getMenuByRoleId(Long roleId){
        LambdaQueryWrapper<RolePower> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(roleId!=null, RolePower::getRoleId, roleId);
        List<RolePower> rolePowerList = rolePowerMapper.selectList(wrapper);
        //权限id列表
        List<Integer> powerIdList = rolePowerList.stream().map(RolePower::getPowerId).collect(Collectors.toList());
//        System.out.println(powerIdList);
        powerIdList.add(10);
        LambdaQueryWrapper<PowerMenu> pmWrapper=new LambdaQueryWrapper<>();
        pmWrapper.in(PowerMenu::getPowerId,powerIdList);
        List<PowerMenu> powerMenus = powerMenuMapper.selectList(pmWrapper);
        List<Integer> menuIds =powerMenus .
                stream().
                map(PowerMenu::getMenuId).
                collect(Collectors.toList());
//        menuIds.add(15);
//        System.out.println(menuIds);
        LambdaQueryWrapper<Menu> menuWrapper=new LambdaQueryWrapper<>();
        menuWrapper.in(Menu::getMenuId,menuIds);
        List<Menu> menuList = menuMapper.selectList(menuWrapper);
        List<Integer> menuParentIds =menuList.
                stream().
                map(Menu::getParentId).
                collect(Collectors.toList());
//        查找父菜单列表
        LambdaQueryWrapper<Menu> parentMenuWrapper=new LambdaQueryWrapper<>();
        parentMenuWrapper.in(Menu::getMenuId,menuParentIds);
        List<Menu> parentList = menuMapper.selectList(parentMenuWrapper);
        System.out.println("parent"+ parentList);
        List<Menu> combinedList = new ArrayList<>();
        combinedList.addAll(parentList);
        combinedList.addAll(menuList);

        List<MenuLabelVo> menus = BeanCopyUtils.copyBeanList(combinedList, MenuLabelVo.class);

        System.out.println("1111111112"+menus);
        // 构建树形结构
//        List<MenuLabelVo> menuLabelVoList = new ArrayList<>();
        List<MenuLabelVo> menuLabelVoList = builderLabelMenuTree(menus,1);

        MenuModifyVo menuModifyVo = new MenuModifyVo();
        List<String> checkedKeys = new LinkedList<>(); // 假设你有一种方法来获取 checkedKeys
        menuModifyVo.setCheckedKeys(checkedKeys);
        menuModifyVo.setMenus(menuLabelVoList);

        // 返回 MenuModifyVo 对象
        System.out.println("2222222"+menuModifyVo);
        return menuModifyVo;
    }


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

    /**
     * 创建菜单树
     * @param menus
     * @param level
     * @return
     */
    private List<MenuLabelVo> builderLabelMenuTree(List<MenuLabelVo> menus, int level) {
        List<MenuLabelVo> menuTree = menus.stream()
                .filter(menu -> Objects.equals(menu.getMenuLevel(), level))
                .map(menu -> menu.setChildren(getMenusChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 创建子级菜单
     * @param menu
     * @param menus
     * @return
     */
    private List<MenuLabelVo> getMenusChildren(MenuLabelVo menu, List<MenuLabelVo> menus) {
        List<MenuLabelVo> childrenList = menus.stream()
//                .filter(m -> m.getParentId().equals(menu.getMenuId()))
                .filter(m -> Objects.equals(m.getParentId(), menu.getMenuId()))
                .map(m-> m.setChildren(getMenusChildren(m, menus)))
                .collect(Collectors.toList());
        return childrenList;
    }

}
