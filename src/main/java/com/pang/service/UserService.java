package com.pang.service;


import com.pang.entity.User;

import java.util.List;

public interface UserService {

    User login(User user);

    User findByUserName(String username);

    //用户注册
    void register(User user);

    //修改用户
    void update(User user);

    //修改回显
    User findById(String id);

    //查询总条数
    Integer findTotals();

    //参数1：当前页  //参数2每页显示记录数
    List<User> findByPage(Integer page, Integer rows);

    //查询职位为员工的人
    List<User> queryUserByRole(String role);
}
