package com.example.springdemo.service;

import com.example.springdemo.mybatis.dao.UserMapper;
import com.example.springdemo.mybatis.entity.User;

//@Autowired 是一个 Spring 注解，用于进行依赖注入。通过在需要依赖的字段、构造函数或方法上加上 @Autowired 注解，
// Spring 将会自动将相应的依赖对象注入进去，无需手动实例化。
import org.springframework.beans.factory.annotation.Autowired;

//@Service 是一个 Spring 注解，用于标记一个类为服务类（Service class）。通过将 @Service 注解应用在类上，Spring 将会自动扫描该类，
// 并创建一个该类的实例，以供其他组件使用。
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            if (user.getRole().contains("ADMIN")) { // 判断用户是否包含 "admin" 角色
                roles.add("ROLE_ADMIN");
            }
        }
        return roles;
    }
}

