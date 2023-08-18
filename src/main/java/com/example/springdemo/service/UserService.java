package com.example.springdemo.service;

import com.example.springdemo.mybatis.entity.User;

import java.util.List;


public interface UserService {
    User getUserByUsername(String username);
    List<String> getUserRoles(String username);
}

