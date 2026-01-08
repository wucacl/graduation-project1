package com.stusym.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // 后续如果有图片上传功能，可以在这里配置资源映射
    // 暂时留空
}