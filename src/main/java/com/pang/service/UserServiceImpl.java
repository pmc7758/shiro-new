package com.pang.service;


import com.pang.mapper.UserMapper;
import com.pang.entity.User;
import com.pang.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 客户端用户表 服务实现类
 * </p>
 *
 * @author zhangxiaoxiang
 * @since 2019-07-12
 */
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    //用户登录
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        //根据账号密码查询
        User userDB = userMapper.login(user);
        if (userDB != null) {
            return userDB;
        }
        throw new RuntimeException("登录失败");
    }

    /**
     * 通过用户姓名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        User userDB = userMapper.queryUserByUsername(username);
        return userDB;
    }

    //用户注册
    @Override
    public void register(User user) {
        User userDB = userMapper.queryUserByUsername(user.getUsername());
        if (userDB == null) {
            userMapper.save(user);
        } else {
            throw new RuntimeException("用户名已存在");
        }
    }

    //修改自己的消息
    @Override
    public void update(User user) {
        User userDB = userMapper.queryUserByUsername(user.getUsername());
        userMapper.update(user);
    }


    @Override
    public Integer findTotals() {
        return userMapper.findTotals();
    }


    //分页的业务代码
    @Override
    public List<User> findByPage(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        //start和rows是数据库查询结果起始记录，比如10和10，代表从第十条记录，展示十条记录，也就是10-20之间
        return userMapper.findByPage(start, rows);
    }

    @Override
    public User findById(String id) {
        return userMapper.findById(id);
    }


    @Override
    public List<User> queryUserByRole(String role) {
        return userMapper.queryUserByRole(role);
    }
}
