//package com.example.springdemo.config;
//
//import com.example.springdemo.interceptor.LoginAccessInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//// WebConfig 类用于配置拦截器。
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginAccessInterceptor loginAccessInterceptor;
//
//    @Override
//    // addInterceptors 方法是 WebMvcConfigurer 接口中的一个方法，用于添加拦截器。
//    public void addInterceptors(InterceptorRegistry registry) {
//        //registry.addInterceptor(loginAccessInterceptor) 用于向 InterceptorRegistry 注册 LoginAccessInterceptor 拦截器。
//        registry.addInterceptor(loginAccessInterceptor)
//                // .addPathPatterns("/login") 用于指定 LoginAccessInterceptor 拦截器要拦截的路径为 /login。
//                .addPathPatterns("/login");
//    }
//
//    // 其他配置...
//}
