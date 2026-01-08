package com.stusym.controller;

import com.stusym.common.Result;
import com.stusym.dto.PasswordChangeDTO;
import com.stusym.dto.UserUpdateDTO;
import com.stusym.entity.User;
import com.stusym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 修改昵称
    @PostMapping("/update")
    public Result updateInfo(@RequestBody UserUpdateDTO dto) {
        User user = userService.updateUserInfo(dto);
        return Result.success(user);
    }

    // 修改密码
    @PostMapping("/password")
    public Result changePassword(@RequestBody PasswordChangeDTO dto) {
        userService.changePassword(dto);
        return Result.success("密码修改成功");
    }
}