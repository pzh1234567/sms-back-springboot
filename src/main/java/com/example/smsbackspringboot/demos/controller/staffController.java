package com.example.smsbackspringboot.demos.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Role;
import com.example.smsbackspringboot.demos.entiy.Staff;
import com.example.smsbackspringboot.demos.service.roleService;
import com.example.smsbackspringboot.demos.service.staffRoleService;
import com.example.smsbackspringboot.demos.util.BeanCopyUtils;
import com.example.smsbackspringboot.demos.vo.param.AddStaffParam;
import com.example.smsbackspringboot.demos.vo.param.LoginParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class staffController {
    @Autowired
    com.example.smsbackspringboot.demos.service.staffService staffService;

    @Autowired
    com.example.smsbackspringboot.demos.service.staffRoleService staffRoleService;

    @Autowired
    com.example.smsbackspringboot.demos.service.roleService roleService;

    /**
     * 新增员工信息
     */
    @ApiOperation(value = "获取员工列表")
    @GetMapping("/user/staff/getstafflist")
    public Result addStaff(Long roleId, String name, Integer gender, Integer pageNum, Integer pageSize) {
        System.out.println("roleID11111" + roleId);
        Result result = staffService.getStaffList(roleId, name, gender, pageNum, pageSize);
        return result;
    }

    /**
     * 删除员工信息
     */
    @ApiOperation(value = "删除员工信息")
    @DeleteMapping("/user/staff/deleteStaff/{id}")
    public Result deleteStaffById(@PathVariable("id") Long id) {
        int count = staffService.deletedStaffById(id);
        if (count > 0) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation(value = "编辑员工信息")
    @PutMapping("/user/staff/editStaffInfo")
    public Result editStaffById(@RequestBody AddStaffParam addStaffParam) {
        System.out.println("2222222" + addStaffParam);
        int count = staffService.updateStaffInfoById(addStaffParam);
        if (count > 0) {
            return Result.success("注册成功");
        }else if(count == -1) {
            return Result.error("该账号已经被注册");
        }else if(count == -2) {
            return Result.error("该电话已经被注册");
        }else {
            return Result.error("编辑失败");
        }
    }

    @ApiOperation(value = "添加员工信息")
    @PostMapping("/user/staff/addStaffInfo")
    public Result addStaffInfo(@RequestBody AddStaffParam addStaffParam) {
        System.out.println("2222222" + addStaffParam);

        int count = staffService.addStaffInfo(addStaffParam);
        if (count > 0) {
            return Result.success("注册成功");
        }else if(count == -1) {
            return Result.error("该账号已经被注册");
        }else if(count == -2) {
            return Result.error("该电话已经被注册");
        }else {
            return Result.error("添加失败");
        }
//        Role role = roleService.getRoleByName(addStaffParam.getRoleName());
//        int count = staffRoleService.addStaffRole(staffId, addStaffParam.getRoleId());
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result Login(@RequestBody LoginParam loginParam){
        Staff staff = staffService.getStaffInfoByAccount(loginParam.getAccount());
        System.out.println(staff.getPassword());
        if(loginParam.getPassword().equals( staff.getPassword())){
            StpUtil.login(staff.getAccount());
            return Result.success(StpUtil.getTokenValue());
        }else {
            return Result.error("密码输入错误");
        }
    }


    @GetMapping("/islogin")
    public String test(){
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    // 用户登出接口
    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    public String logout() {
        // 登出操作
        StpUtil.logout();
        return "登出成功";
    }

    @ApiOperation(value = "根据用户账号获取用户信息")
    @GetMapping("/user/staff/getStaffbyAccount")
    public Result getStaffByAccount(String account){
        Staff staff = staffService.getStaffInfoByAccount(account);
        return Result.success(staff);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/user/staff/staffRegister")
    public Result resultRegister(@RequestBody AddStaffParam addStaffParam){
        System.out.println("add"+addStaffParam);
        int count = staffService.addStaffInfo(addStaffParam);
        if (count > 0) {
            return Result.success("注册成功");
        }else if(count == -1) {
            return Result.error("该账号已经被注册");
        }else if(count == -2) {
            return Result.error("该电话已经被注册");
        }else {
            return Result.error("注册失败");
        }
    }

    @ApiOperation(value="根据id查询员工信息")
    @GetMapping("/user/staff/getStaffInfoById/{id}")
    public Result getStaffInfoById(@PathVariable Long id){
        Staff staff = staffService.getStaffInfoById(id);
        return Result.success(staff);
    }
}
