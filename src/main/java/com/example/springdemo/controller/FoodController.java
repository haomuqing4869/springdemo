package com.example.springdemo.controller;

import com.example.springdemo.service.FoodService;
import com.example.springdemo.mybatis.entity.Food;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;
    private final StringRedisTemplate redisTemplate;
    boolean isRedisRunning = false;

    @Autowired
    public FoodController(FoodService foodService, StringRedisTemplate redisTemplate) {
        this.foodService = foodService;
        this.redisTemplate = redisTemplate;

        // 判断Redis是否启动
        try {
            /*
            *   redisTemplate: Redis模板对象，用于访问和操作Redis数据。
                getConnectionFactory(): 从Redis模板中获取Redis连接工厂对象，用于创建Redis连接。
                getConnection(): 通过连接工厂获取一个Redis连接对象，用于与Redis服务器进行通信。
            * */
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            if ("PONG".equals(connection.ping())) {
                this.isRedisRunning = true;
            }
        } catch (Exception e) {
            // 连接异常，Redis 未启动或连接失败
        }
        if (this.isRedisRunning) {
            System.out.println("Redis is running");
        } else {
            System.out.println("Redis is not running");
        }
    }

    @RequestMapping("/get/all")
    public String getAllFoods() throws JsonProcessingException {
        List<Food> foods = foodService.getALL();
        // 创建一个 ObjectMapper 对象 mapper，它是 Jackson 库的核心类，用于处理对象与 JSON 之间的转换。
        ObjectMapper mapper = new ObjectMapper();

        /*
        *   调用 writerWithDefaultPrettyPrinter() 方法来获取一个能够美化输出 JSON 格式的 ObjectWriter 对象。
        *   这样生成的 JSON 字符串在可读性上更好。
        *
        *   调用 writeValueAsString(foods) 方法将 foods 对象转换为 JSON 格式的字符串。
        * */
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(foods);
    }

    @RequestMapping("/get/{id}")
    public FoodResponse getFoodById(@PathVariable Integer id) {
        if (!isRedisRunning) {//redis未启动
            // Redis未启动，直接从数据库获取数据
            Food food = foodService.selectByPrimaryKey(id);
            return new FoodResponse(food, false);
        }
        else{ // redis已启动

            // 先尝试从缓存中获取数据
            String key = "food:" + id;
            // 通过 redisTemplate 对象调用 opsForValue() 方法，获取一个可以操作 Redis 字符串类型的操作对象。
            // 调用 get(key) 方法，其中 key 是要获取的键名，从 Redis 中获取与该键相关联的值。
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
        if (!isRedisRunning){//redis已启动

            // 清除所有以"food:"开头的缓存数据
            // 通配符 "*" 表示匹配任意字符（包括空字符），所以这个模式可以匹配以 "food:" 开头的任意键。
            Set<String> keys = redisTemplate.keys("food:*");
            if (keys != null && !keys.isEmpty()) { // 判断keys非空且非空集合
                redisTemplate.delete(keys);
            }
        }
    }

    //序列化
    private String serializeFood(Food food) {
        // 将Food对象序列化成字符串
        // 这里可以使用JSON、XML等序列化方式，根据需要选择合适的方式
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(food);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    //反序列化
    private Food deserializeFood(String data) {
        // 将字符串反序列化为Food对象
        // 这里需要和serializeFood方法中的序列化方式保持一致
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
