package com.stusym.mapper;

import com.stusym.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 根据用户名查询用户
    User selectByUsername(@Param("username") String username);

    // 新增：根据ID查询用户
    User selectById(@Param("id") Long id);

    // 插入新用户
    int insert(User user);

    // 更新用户信息 (后面修改昵称会用到)
    int update(User user);
}