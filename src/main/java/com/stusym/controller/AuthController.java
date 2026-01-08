package com.stusym.controller;

import com.stusym.common.JwtUtils;
import com.stusym.common.Result;
import com.stusym.dto.LoginDTO;
import com.stusym.dto.RegisterDTO;
import com.stusym.entity.User;
import com.stusym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        // 打印日志方便调试
        System.out.println("收到登录请求: " + loginDTO.getUsername());
        try {
            User user = userService.login(loginDTO);
            String token = jwtUtils.generateToken(user.getUsername());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", user);
            return Result.success(data);
        } catch (Exception e) {
            e.printStackTrace(); // 后端控制台打印错误堆栈
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDTO registerDTO) {
        System.out.println("收到注册请求: " + registerDTO.getUsername());
        try {
            userService.register(registerDTO);
            return Result.success("注册成功");
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}