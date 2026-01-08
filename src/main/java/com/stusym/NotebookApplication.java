// 位置: src/main/java/com/stusym/NotebookApplication.java
package com.stusym;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// 1. 确保扫描到 Mapper 接口
@MapperScan("com.stusym.mapper")

public class NotebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotebookApplication.class, args);
    }

}