package com.pang.mapper;

import com.pang.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User,String> {

    User queryUserByUsername(String username);

    User login(User user);

    void save(User user);

    void update(User user);

    User findById(String id);

    Integer findTotals();

    List<User> queryUserByRole(String role);

}
