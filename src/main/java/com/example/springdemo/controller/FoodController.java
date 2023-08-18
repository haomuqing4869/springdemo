package com.example.springdemo.controller;

import com.example.springdemo.service.FoodService;
import com.example.springdemo.mybatis.entity.Food;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public FoodController(FoodService foodService, StringRedisTemplate redisTemplate) {
        this.foodService = foodService;
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping("/get/{id}")
    public FoodResponse getFoodById(@PathVariable Integer id) {
        // 先尝试从缓存中获取数据
        String key = "food:" + id;
        String cachedData = redisTemplate.opsForValue().get(key);
        if (cachedData != null) {
            // 缓存命中
            Food food = deserializeFood(cachedData);
            return new FoodResponse(food, true);
        } else {
            // 缓存未命中，从数据库中获取数据
            Food food = foodService.selectByPrimaryKey(id);
            // 将数据存入缓存
            redisTemplate.opsForValue().set(key, serializeFood(food));
            return new FoodResponse(food, false);
        }
    }

    @RequestMapping(value="/create/", method = RequestMethod.POST)
    public String createFood(@RequestBody Food food) {
        int rows = foodService.insert(food);
        if (rows > 0) {
            // 清除缓存
            clearCache();
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
            // 清除缓存
            clearCache();
            return "Food updated successfully";
        } else {
            return "Failed to update food";
        }
    }

    @RequestMapping("/delete/{id}")
    public String deleteFood(@PathVariable Integer id) {
        int rows = foodService.deleteByPrimaryKey(id);
        if (rows > 0) {
            // 清除缓存
            clearCache();
            return "Food deleted successfully";
        } else {
            return "Failed to delete food";
        }
    }

    private void clearCache() {
        // 清除所有以"food:"开头的缓存数据
        Set<String> keys = redisTemplate.keys("food:*");
        if (keys != null && !keys.isEmpty()) { // 判断keys非空且非空集合
            redisTemplate.delete(keys);
        }
    }

    private String serializeFood(Food food) {
        // 将Food对象序列化成字符串
        // 这里可以使用JSON、XML等序列化方式，根据需要选择合适的方式
        // 以下是使用JSON序列化的示例
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(food);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Food deserializeFood(String data) {
        // 将字符串反序列化为Food对象
        // 这里需要和serializeFood方法中的序列化方式保持一致
        // 以下是使用JSON反序列化的示例
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(data, Food.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class FoodResponse {
        private Food food;
        private boolean cached;

        public FoodResponse(Food food, boolean cached) {
            this.food = food;
            this.cached = cached;
        }

        public Food getFood() {
            return food;
        }

        public void setFood(Food food) {
            this.food = food;
        }

        public boolean isCached() {
            return cached;
        }

        public void setCached(boolean cached) {
            this.cached = cached;
        }
    }
}
