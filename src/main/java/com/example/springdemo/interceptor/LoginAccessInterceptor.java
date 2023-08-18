//package com.example.springdemo.interceptor;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//// 创建一个名为 LoginAccessInterceptor 的拦截器类，并实现 HandlerInterceptor 接口。
//public class LoginAccessInterceptor implements HandlerInterceptor {
//
//    @Override
//    // 拦截器的 preHandle 方法实现，它会在请求到达处理器方法之前执行。
//    // 在设计应用程序时，应该避免过于依赖 Referer 头部字段，因为它是由客户端自行设置的，可能受到伪造或篡改的风险。建议采用多种验证方式来增强安全性。
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 通过 request.getHeader("Referer") 获取请求的 Referer 头部字段，该字段包含了当前请求的来源页面的 URL。
//        String referer = request.getHeader("Referer");
//
//        // 如果 referer 为空或者不满足条件，表示来源页面不是以 /api/user 或 /api/admin 结尾，这意味着直接访问 /login 是不被允许的。
//        if (referer == null || !(referer.endsWith("/api/user") || referer.endsWith("/api/admin"))) {
//            // 如果来源页面不是以 /api/user 或 /api/admin 结尾的，则禁止直接访问 /login
//
//            // response.sendError 方法返回一个 HTTP 错误响应，具体是 403 Forbidden，
//            // 同时提供一个错误信息 "Direct access to /login is not allowed."。
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Direct access to /login is not allowed.");
//            return false;
//        }
//
//        return true;
//    }
//
//    // 其他方法...
//}
