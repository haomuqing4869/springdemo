package com.example.springdemo.service;

//import com.example.springdemo.mybatis.dao.FoodMapper;
import com.example.springdemo.mybatis.entity.Food;

import java.util.List;

//@Autowired 是一个 Spring 注解，用于进行依赖注入。通过在需要依赖的字段、构造函数或方法上加上 @Autowired 注解，
// Spring 将会自动将相应的依赖对象注入进去，无需手动实例化。
//import org.springframework.beans.factory.annotation.Autowired;

//@Service 是一个 Spring 注解，用于标记一个类为服务类（Service class）。通过将 @Service 注解应用在类上，Spring 将会自动扫描该类，
// 并创建一个该类的实例，以供其他组件使用。
//import org.springframework.stereotype.Service;

//import java.util.Map;


public interface FoodService {
    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    List<Food> getALL();
}