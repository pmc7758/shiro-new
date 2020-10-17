package com.pang.service;


import com.pang.mapper.UserMapper;
import com.pang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
