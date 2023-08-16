package com.example.springdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.springdemo.mybatis.dao")
public class SpringdemoApplication {
    //http://127.0.0.1:8088/springdemo/service/getFoodInfo
    //http://127.0.0.1:8088/springdemo/food
    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }

}
