package com.example.springdemo.config;

import com.example.springdemo.mybatis.entity.User;
import com.example.springdemo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF（跨站请求伪造）防护功能，即不对跨站请求进行验证
//                .csrf().disable()
                // 开始对请求进行授权的配置
                .authorizeRequests()
                // 允许对 "/api/public" 路径的请求进行公共访问，即无需任何身份验证即可访问。
                .antMatchers("/api/public").permitAll()
                // 要求对 "/api/user" 路径的请求具有 "USER" 角色的身份验证。
                .antMatchers("/api/user").hasRole("USER")
                // 要求对 "/api/admin" 路径的请求具有 "ADMIN" 角色的身份验证。
                .antMatchers("/api/admin").hasRole("ADMIN")
                // 设置与 "/springdemo/food/" 相关的所有路径无需认证
                .antMatchers("/food/**").permitAll()
                // 要求对除上述指定路径之外的所有请求进行身份验证。
                .anyRequest().authenticated()
                .and()
                // .httpBasic();
                /*
                * .httpBasic() 是 Spring Security 中的一种身份验证方式。它使用 HTTP 基本身份验证来验证用户的身份。
                *
                * 当配置 http.httpBasic() 并启用时，服务器将要求用户在发送请求时提供用户名和密码，
                * 并将其包含在请求的头部中（通常是 Base64 编码的形式）。然后，服务器将使用这些凭证进行身份验证。
                *
                * 这种身份验证方式适用于通过浏览器或其他客户端应用程序与服务器进行通信的场景。它需要用户输入用户名和密码，
                * 并且这些凭证会在每个请求中发送，因此需要通过 HTTPS 或其他安全传输方式来确保凭证的安全性。
                * */
                // 配置登录页面的路径为 "/login"，并允许所有用户访问该页面。
                .formLogin().loginPage("/login").permitAll()
                .and()
                // 配置登出操作的路径，并允许所有用户访问该路径。
                .logout().permitAll();
    }

    @Override
    // 配置身份验证管理器（AuthenticationManagerBuilder）
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userDetailsService()) 的作用是指定了一个自定义的 UserDetailsService。
        // UserDetailsService 是 Spring Security 提供的一个接口，用于从特定的数据源（如数据库）中获取用户的详细信息，包括用户名、密码和权限等。
        // userDetailsService() 方法是一个你需要实现的自定义方法，用于返回一个 UserDetailsService 实例。
        // passwordEncoder() 方法则指定了密码加密器（PasswordEncoder）。密码加密器用于加密和验证用户密码，以提高安全性。
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    // userDetailsService() 方法是一个返回 UserDetailsService 实例的 Bean 方法。
    public UserDetailsService userDetailsService() {
        return username -> {
            // 通过传入的用户名（username）从 userService 中获取对应的用户信息
            User user = userService.getUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            // 通过调用 userService 的方法获取用户的角色（roles）
            List<String> roles = userService.getUserRoles(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                // 将每个角色转换为 Spring Security 的 GrantedAuthority 接口的实现类 SimpleGrantedAuthority，
                // 并添加到一个权限列表（authorities）中。
                authorities.add(new SimpleGrantedAuthority(role));
            }

            // 创建并返回一个 org.springframework.security.core.userdetails.User 对象。
            // 该对象是 Spring Security 提供的默认 UserDetailsService 返回的用户对象，包含了用户名、密码和权限列表等信息。
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        };
    }

    @Bean
    // passwordEncoder() 方法是一个返回 PasswordEncoder 实例的 Bean 方法
    public PasswordEncoder passwordEncoder() {
        //使用 NoOpPasswordEncoder 不会对密码进行任何加密或哈希处理，这意味着密码以明文形式存储在数据库中。这不是推荐的做法，因为不安全。
        // 通常情况下，应该使用安全的密码加密器（如 BCryptPasswordEncoder）对密码进行加密，以保护用户凭证的安全性。
//        return new BCryptPasswordEncoder();

        // 使用 NoOpPasswordEncoder，即不对密码进行任何加密
        return NoOpPasswordEncoder.getInstance();
    }
}
