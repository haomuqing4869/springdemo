package com.example.springdemo.service;

import com.example.springdemo.mybatis.dao.FoodMapper;
import com.example.springdemo.mybatis.entity.Food;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

/*
FoodMapper 是一个用于操作数据库的映射器（Mapper），它提供了对 Food 实体类的数据库操作方法。
在 FoodServiceImpl 类中，通过在构造函数上使用 @Autowired 注解，将 FoodMapper 对象注入到类的成员变量 foodMapper 中。
然后，在类中实现了 FoodService 接口中定义的各个方法。这些方法的实现都是调用 foodMapper 对象的相应方法来进行数据库操作。
* */
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

