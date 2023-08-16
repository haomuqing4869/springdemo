package com.example.springdemo.mybatis.dao;

import java.util.Map;

public interface TFoodMapper {
    //Map<String, Object> 在实际开发中常用于存储和传递动态的键值对数据，适用于各种场景，如数据库查询结果的返回、配置信息的读取等。
    Map<String,Object> queryFood();
}
