package com.stusym.service.impl;

import com.stusym.dto.LoginDTO;
import com.stusym.dto.PasswordChangeDTO;
import com.stusym.dto.RegisterDTO;
import com.stusym.dto.UserUpdateDTO;
import com.stusym.entity.User;
import com.stusym.mapper.UserMapper;
import com.stusym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(LoginDTO loginDTO) {
        User user = userMapper.selectByUsername(loginDTO.getUsername());

        // 核心修改：合并错误提示，防止恶意扫描用户名
        if (user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            // 这里就是你要求的报错信息
            throw new RuntimeException("用户名或密码错误");
        }
        return user;
    }

    @Override
    public void register(RegisterDTO dto) {
        if (userMapper.selectByUsername(dto.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setNickname(dto.getUsername());
        userMapper.insert(user);
    }

    @Override
    public User updateUserInfo(UserUpdateDTO dto) {
        User currentUser = userMapper.selectById(dto.getId());
        if (currentUser == null) throw new RuntimeException("用户异常");

        User update = new User();
        update.setId(dto.getId());
        update.setNickname(dto.getNickname());

        if (dto.getUsername() != null && !dto.getUsername().isEmpty() && !dto.getUsername().equals(currentUser.getUsername())) {
            User exist = userMapper.selectByUsername(dto.getUsername());
            if (exist != null) {
                throw new RuntimeException("该用户名已存在，请换一个");
            }
            update.setUsername(dto.getUsername());
        }

        userMapper.update(update);
        return userMapper.selectById(dto.getId());
    }

    @Override
    public void changePassword(PasswordChangeDTO dto) {
        User user = userMapper.selectById(dto.getId());
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("旧密码错误");
        }
        User update = new User();
        update.setId(user.getId());
        update.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userMapper.update(update);
    }
}