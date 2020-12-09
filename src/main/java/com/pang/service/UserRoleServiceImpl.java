package com.pang.service;


import com.pang.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService{


    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public void update(String userid,String roleid) {
        userRoleMapper.update(userid,roleid);
    }

    @Override
    public void add(String userid, String roleid) {
        userRoleMapper.add(userid,roleid);
    }
}
