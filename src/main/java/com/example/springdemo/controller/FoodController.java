package com.example.springdemo.controller;

import com.example.springdemo.service.FoodServiceImpl;
import com.example.springdemo.service.FoodService;
import com.example.springdemo.mybatis.entity.Food;

//用于实现依赖注入，自动装配Bean。
import org.springframework.beans.factory.annotation.Autowired;
//用于定义HTTP请求映射的注解。
import org.springframework.web.bind.annotation.*;
//用于标识一个类是一个RESTful风格的控制器。

//用于创建一个键值对的集合，常用于封装方法的返回值或存储临时数据。
import java.util.HashMap;
//表示一个映射接口，具有键值对的特性。
import java.util.Map;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService; // 注入 FoodService

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @RequestMapping("/get/{id}")
    public Food getFoodById(@PathVariable Integer id) {
        return foodService.selectByPrimaryKey(id);
    }

    @RequestMapping(value="/create/", method = RequestMethod.POST)
    public String createFood(@RequestBody Food food) {
        int rows = foodService.insert(food);
        if (rows > 0) {
            return "Food created successfully";
        } else {
            return "Failed to create food";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String updateFood(@PathVariable Integer id, @RequestBody Food food) {
        food.setId(id);
        int rows = foodService.updateByPrimaryKey(food);
        if (rows > 0) {
            return "Food updated successfully";
        } else {
            return "Failed to update food";
        }
    }

    @RequestMapping("/delete/{id}")
    public String deleteFood(@PathVariable Integer id) {
        int rows = foodService.deleteByPrimaryKey(id);
        if (rows > 0) {
            return "Food deleted successfully";
        } else {
            return "Failed to delete food";
        }
    }
}

