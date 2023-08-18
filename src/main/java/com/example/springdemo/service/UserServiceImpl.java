package com.example.springdemo.service;

import com.example.springdemo.mybatis.dao.UserMapper;
import com.example.springdemo.mybatis.entity.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
UserMapper 是一个用于操作数据库的映射器（Mapper），它提供了对用户实体类的数据库操作方法。
在 UserServiceImpl 类中，通过在构造函数上使用 @Autowired 注解，将 UserMapper 对象注入到类的成员变量 userMapper 中。
* */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<String> getUserRoles(String username) {
        List<String> roles = new ArrayList<>();
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            roles.add("ROLE_USER");
            // 判断用户是否包含 "ADMIN" 角色（contains方法是大小写敏感的）
            if (user.getRole().contains("ADMIN")) {
                roles.add("ROLE_ADMIN");
            }
        }
        return roles;
    }
}

