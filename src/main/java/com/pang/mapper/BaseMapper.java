package com.pang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BaseMapper<T, K> {


    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    //博客收藏有解释，参数要与映射中#{}值一样,才能正确传参

    Integer findTotals();

}
