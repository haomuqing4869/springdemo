package com.example.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller // @Controller 注解告诉 Spring Boot 使用模板引擎来解析和返回模板页面。
//@RestController // 使用@RestController 注解，这意味着将返回JSON字符串作为响应，而不是模板页面。
//@RequestMapping("/api")
public class DataController {

    // 处理 /login 页面的请求
    @GetMapping("/login")
    public String loginPage() {
        /*
        * 拦截器会在请求到达 Controller 方法之前进行拦截和处理，对请求进行相关的操作。
        * 然后，请求会继续流转到对应的 Controller 方法进行处理。
        * */
        return "login";
    }

    @GetMapping("/api/user")
    @ResponseBody // 以JSON字符串格式返回HTTP响应
    public String getUserData() {
        return "User API";
    }

    @GetMapping("/api/admin")
    @ResponseBody // 以JSON字符串格式返回HTTP响应
    public String getAdminData() {
        return "Admin API";
    }

    @GetMapping("/api/public")
    @ResponseBody // 以JSON字符串格式返回HTTP响应
    public String getData() {
        return "Public API";
    }
}
