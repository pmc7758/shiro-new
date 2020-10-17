package com.pang.controller;


import com.pang.entity.User;
import com.pang.service.UserService;
import com.pang.utils.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @GetMapping("/user/login")
    public Map<String, Object> login(User user) {

        Map<String, Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            //生成JWT的令牌
            payload.put("username", userDB.getUsername());
            String token = JwtUtil.sign(payload,userDB.getPassword());
            map.put("state", true);
            map.put("msg", "认证成功");
            map.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @RequiresRoles(value = {"总经理"})
    @GetMapping("user/login/test1")
    public Map<String, Object> test1() {
        Map<String, Object> map = new HashMap<>();
        //业务代码
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }

    @RequiresRoles("总经理")
    @GetMapping("user/login/test2")
    public Map<String, Object> test2() {
        Map<String, Object> map = new HashMap<>();
        //业务代码
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }


    @GetMapping("user/login/test3")
    public Map<String, Object> test3() {
        Map<String, Object> map = new HashMap<>();
        //业务代码
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }


    @RequiresPermissions("sys:x:add")
    @GetMapping("user/login/test4")
    public Map<String, Object> test4() {
        Map<String, Object> map = new HashMap<>();
        //业务代码
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }
}
