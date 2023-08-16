package com.example.springdemo.controller;

import com.example.springdemo.service.FoodService;

//用于实现依赖注入，自动装配Bean。
import com.example.springdemo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
//用于定义HTTP请求映射的注解。
import org.springframework.web.bind.annotation.RequestMapping;
//用于标识一个类是一个RESTful风格的控制器。
import org.springframework.web.bind.annotation.RestController;

//用于创建一个键值对的集合，常用于封装方法的返回值或存储临时数据。
import java.util.HashMap;
//表示一个映射接口，具有键值对的特性。
import java.util.Map;

@RestController
@RequestMapping(value = "/service")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/getFoodInfo")
    public Map<String,Object> getFoodInfo(){
        Map<String, Object> resultMap = new HashMap<>();

        resultMap = foodService.queryFood();

        return resultMap;
    }
}
