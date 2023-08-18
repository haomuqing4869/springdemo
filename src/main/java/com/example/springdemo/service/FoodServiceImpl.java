package com.example.springdemo.service;

import com.example.springdemo.mybatis.dao.FoodMapper;
import com.example.springdemo.mybatis.entity.Food;

//@Autowired 是一个 Spring 注解，用于进行依赖注入。通过在需要依赖的字段、构造函数或方法上加上 @Autowired 注解，
// Spring 将会自动将相应的依赖对象注入进去，无需手动实例化。
import org.springframework.beans.factory.annotation.Autowired;

//@Service 是一个 Spring 注解，用于标记一个类为服务类（Service class）。通过将 @Service 注解应用在类上，Spring 将会自动扫描该类，
// 并创建一个该类的实例，以供其他组件使用。
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodMapper foodMapper; // 注入 FoodMapper

    @Autowired
    public FoodServiceImpl(FoodMapper foodMapper) {
        this.foodMapper = foodMapper;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return this.foodMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Food record) {
        return foodMapper.insert(record);
    }

    @Override
    public int insertSelective(Food record) {
        return foodMapper.insertSelective(record);
    }

    @Override
    public Food selectByPrimaryKey(Integer id) {
        return foodMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Food record) {
        return foodMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Food record) {
        return foodMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Food> getALL() {
        return foodMapper.selectALL();
    }
}

