package com.pang.controller;


import com.pang.service.UserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRoleController {


    @Autowired
    private UserRoleService userRoleService;


    //所有人都可以被修改角色权限
    @RequiresRoles("总经理")
    @RequiresPermissions("sys:y:upd")
    @GetMapping("user/login/role/update")
    public Map<String,Object> update(String userid,String roleid){
        Map<String,Object> map = new HashMap<>();
        try {
            userRoleService.update(userid,roleid);
            map.put("state",true);
            map.put("msg","修改成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","修改失败");
        }
        return map;
    }


    //用户被授予角色权限
    @RequiresRoles("总经理")
    @RequiresPermissions("sys:y:upd")
    @GetMapping("user/login/role/add")
    public Map<String,Object> add(String userid,String roleid){
        Map<String,Object> map = new HashMap<>();
        try {
            userRoleService.add(userid,roleid);
            map.put("state",true);
            map.put("msg","授予成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","授予失败");
        }
        return map;
    }



}
