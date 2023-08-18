package com.example.springdemo.service;

import com.example.springdemo.mybatis.entity.Food;

import java.util.List;

/*
    deleteByPrimaryKey(Integer id)：根据主键 id 删除一条记录。
    insert(Food record)：插入一条记录。
    insertSelective(Food record)：选择性地插入一条记录，即只插入非空字段。
    selectByPrimaryKey(Integer id)：根据主键 id 查询一条记录。
    updateByPrimaryKeySelective(Food record)：根据主键选择性地更新一条记录，即只更新非空字段。
    updateByPrimaryKey(Food record)：根据主键更新一条记录。
    getALL()：获取所有的 Food 记录列表。
* */
public interface FoodService {
    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    List<Food> getALL();
}