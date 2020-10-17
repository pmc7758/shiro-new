package com.pang.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface RoleMapper {

    //Set数据结构能去重
    public Set<String> queryRoleNamesByUsername(String username);

}
