package com.pang.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {

    void update(String userid,String roleid);

    void add(String userid,String roleid);
}
