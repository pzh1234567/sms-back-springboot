package com.example.smsbackspringboot.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smsbackspringboot.demos.common.Result;
import com.example.smsbackspringboot.demos.entiy.Role;
import com.example.smsbackspringboot.demos.mapper.roleMapper;
import com.example.smsbackspringboot.demos.vo.commom.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roleService {

    @Autowired
    roleMapper roleMapper;

    /**
     * 根据查询条件获取角色信息
     *
     * @param
     * @return
     */
    public Result getRoleList(String name) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, Role::getRoleName, name);
        List<Role> roleList = roleMapper.selectList(wrapper);
        System.out.println(roleList);
        return Result.success(roleList);
    }

    /**
     * 根据角色名获取角色信息
     *
     * @param name
     * @return
     */

    public Role getRoleByName(String name) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(name != null, Role::getRoleName, name);
        Role role = roleMapper.selectOne(wrapper);
        return role;
    }

    /**
     * 根据id获取角色信息
     * @param id
     * @return
     */
    public Role getRoleById(Long id) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(id != null, Role::getRoleId, id);
        Role role = roleMapper.selectOne(wrapper);
        return role;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    public Result addRole(Role role) {
        //数据库鉴重
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(role.getRoleName() != null, Role::getRoleName, role.getRoleName());
        Role check = roleMapper.selectOne(wrapper);
        if (check != null) {
            return Result.error("该角色已经存在");
        }
        int count = roleMapper.insert(role);
        if (count > 0) {
            return Result.success("角色添加成功");
        } else {
            return Result.error("角色添加失败");
        }
    }

    /**
     * 编辑角色信息
     * @param role
     * @return
     */
    public Result updateRole(Role role){
        //数据库鉴重
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.notIn(role.getRoleId()!=null,Role::getRoleId,role.getRoleId());
        wrapper.eq(role.getRoleName() != null, Role::getRoleName, role.getRoleName());
        Role check = roleMapper.selectOne(wrapper);
        if (check != null) {
            return Result.error("编辑该角色已经存在");
        }
        int count = roleMapper.updateById(role);
        if (count > 0) {
            return Result.success("角色编辑成功");
        } else {
            return Result.error("角色编辑失败");
        }
    }

    public int deleteRole(Long id){
        int count = roleMapper.deleteById(id);
        return count;
    }
}
