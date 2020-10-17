package com.pang.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface PermissionMapper {

    public Set<String> queryPermissionByUsername(String username);
}
