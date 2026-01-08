package com.stusym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 配置密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置安全过滤链
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF (前后端分离通常不需要)
                .csrf(AbstractHttpConfigurer::disable)
                // 配置请求拦截
                .authorizeHttpRequests(auth -> auth
                        // 放行登录和注册接口
                        .requestMatchers("/auth/**").permitAll()
                        // 放行静态资源 (如果有)
                        .requestMatchers("/static/**").permitAll()
                        // 其他所有请求都需要认证 (暂时先全部放行以便调试，后续会改为 authenticated())
                        // .anyRequest().authenticated()
                        .anyRequest().permitAll() // 【调试阶段：暂时允许所有访问，以免被 403 挡住】
                );

        return http.build();
    }
}