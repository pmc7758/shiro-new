package com.pang.mapper;

import com.pang.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User queryUserByUsername(String username);

    User login(User user);

}
