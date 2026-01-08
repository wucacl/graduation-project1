package com.stusym.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private Long id;
    private String nickname;
    private String username; // 新增：允许修改用户名
}