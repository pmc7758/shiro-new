package com.pang.controller;


import com.pang.entity.User;
import com.pang.service.UserService;
import com.pang.utils.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //用户登录
    @GetMapping("user/login")
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



    //用户注册
    @PostMapping("user/register")
    public Map<String, Object> register(@RequestBody User user) { //返回值类型时Map
        Map<String, Object> map = new HashMap<>();
        //1.调用业务方法
        try {
            userService.register(user);
            map.put("state", true);
            map.put("msg", "注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "提示:" + e.getMessage());
        }
        return map;//前端返回一个user对象，有相关信息，Controller调用业务层相关的方法处理数据，返回一个map对象，里面有处理数据后的提示信息
    }


    //用户修改
    @RequiresPermissions("sys:x:upd")
    @GetMapping("user/login/update")
    public Map<String, Object> update(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.update(user);
            map.put("msg", "修改成功");
            map.put("state", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "提示:" + e.getMessage());
        }
        return map;
    }


    //分页查询所有用户
    //查询所有
    @RequiresRoles("总经理")
    @GetMapping("user/login/findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        page = page == null ? 1 : page;
        rows = rows == null ? 1 : rows;

        Map<String, Object> map = new HashMap<>();
        //分页处理
        List<User> user = userService.findByPage(page, rows);
        //计算总页数
        //计算一共多少个人
        Integer totals = userService.findTotals();
        //计算页数
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / +1;

        map.put("user", user);
        map.put("totals", totals);
        //页总数
        map.put("totalsPage", totalPage);
        //当前页
        map.put("page", page);
        return map;
    }


    //修改时的查询回显，前端点击修改时向findOne发送一个id
    @RequiresPermissions("sys:o:list")
    @GetMapping("user/login/findOne")
    public User findOne(String id) {
        User user = userService.findById(id);
        return user;
    }


    //按职位查询人
    @RequiresRoles("部门经理")
    @GetMapping("user/login/queryUserByRole")
    public Map<String, Object> queryUserByRole(String role) {
        role = role == null ? "员工" : role;

        Map<String, Object> map = new HashMap<>();

        List<User> user = userService.queryUserByRole(role);

        map.put("user", user);

        return map;
    }
}
