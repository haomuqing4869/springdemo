package com.example.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    @GetMapping("/user")
    public String getUserData() {
        return "User API";
    }

    @GetMapping("/admin")
    public String getAdminData() {
        return "Admin API";
    }

    @GetMapping("/public")
    public String getData() {
        return "Public API";
    }
}
