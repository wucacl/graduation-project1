package com.stusym.service;

import com.stusym.dto.LoginDTO;
import com.stusym.dto.PasswordChangeDTO;
import com.stusym.dto.RegisterDTO;
import com.stusym.dto.UserUpdateDTO;
import com.stusym.entity.User;

public interface UserService {
    /**
     * 用户登录
     * @param loginDTO 登录参数
     * @return 登录成功的用户信息
     */
    User login(LoginDTO loginDTO);

    /**
     * 用户注册
     * @param registerDTO 注册参数
     */
    void register(RegisterDTO registerDTO);

    // 新增：更新信息
    User updateUserInfo(UserUpdateDTO dto);

    // 新增：修改密码
    void changePassword(PasswordChangeDTO dto);
}