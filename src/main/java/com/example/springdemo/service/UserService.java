package com.example.springdemo.service;

import com.example.springdemo.mybatis.entity.User;

import java.util.List;

/*
    getUserByUsername(String username)：根据用户名 username 获取一个用户对象。这个方法会返回一个 User 对象，其中包含了用户的详细信息。
    getUserRoles(String username)：根据用户名 username 获取该用户的角色列表。这个方法会返回一个字符串列表，列表中的每个元素都代表一个用户角色。
* */
public interface UserService {
    User getUserByUsername(String username);
    List<String> getUserRoles(String username);
}

