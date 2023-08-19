package com.example.springdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.springdemo.mybatis.dao")
public class SpringdemoApplication {
    /*
    *   http://127.0.0.1:8088/springdemo/api/public
    *   http://127.0.0.1:8088/springdemo/api/user
    *   http://127.0.0.1:8088/springdemo/api/admin
    *   http://127.0.0.1:8088/springdemo/login
    *
    *   http://127.0.0.1:8088/springdemo/food/get/1
    *   http://127.0.0.1:8088/springdemo/food/get/all
    *
    *   http://127.0.0.1:8088/springdemo/index.html
    * */
    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }

    /*
    在 Spring Boot 的前后端分离应用中，通常使用以下技术实现：
    -- 后端（Spring Boot）：后端提供数据接口，处理业务逻辑和持久化操作。
       一般使用 Spring MVC 或者 Spring WebFlux 来构建 RESTful API，返回 JSON 或者 XML 格式的数据。
       后端可以使用 Spring Security 来实现身份认证和授权，确保接口的安全性。

    -- 前端（Web 应用）：前端使用 HTML、CSS 和 JavaScript 构建用户界面，并通过 AJAX 或 Fetch API 请求后端的数据接口。
       前端框架如 Vue.js、React、Angular 等可以用于简化开发流程，提供组件化和状态管理的能力。

    -- 数据交互格式：前后端之间通过 JSON 进行数据交互。后端将业务数据以 JSON 格式返回给前端，前端发送的请求也会将数据以 JSON 格式传递给后端。

    -- 跨域请求处理：由于前后端分离应用中，前端通常运行在不同的域名或端口上，因此存在跨域请求的问题。
       可以通过在后端设置响应头允许跨域访问，或者使用反向代理服务器（如 Nginx）进行请求转发来解决跨域问题。

    -- 构建工具和打包：前端代码可以使用 npm 或者 yarn 等包管理工具管理依赖和构建过程。
       前端代码一般会被打包成静态资源，在部署时与后端代码一起进行发布。
    * */
}
