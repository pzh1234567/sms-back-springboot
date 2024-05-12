package com.example.smsbackspringboot.demos.controller;

import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Menu;
import com.example.smsbackspringboot.demos.service.menuService;
import com.example.smsbackspringboot.demos.vo.commom.MenuLabelVo;
import com.example.smsbackspringboot.demos.vo.commom.MenuModifyVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO目录controller
 */

@RestController
public class menuController {
    @Autowired
    menuService menuService;

    /**
     * 获取所有目录（测试）
     * @return
     */
//    @GetMapping("/menu/getallmenu")
//    public Result getAllMenu(){
////        List<Menu> menuList = menuService.getAllMenu();
//        MenuModifyVo menuList = menuService.getAllMenu();
//        return Result.success(menuList);
//    }
    @GetMapping("/menu/getallmenu")
    public Result getAllMenu(Long staffId){
//        List<Menu> menuList = menuService.getAllMenu();
        MenuModifyVo menuList = menuService.getMenu(staffId);
        return Result.success(menuList);
    }
}
