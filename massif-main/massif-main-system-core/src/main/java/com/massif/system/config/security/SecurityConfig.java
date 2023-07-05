package com.massif.system.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .formLogin()    // 表单登录
                // 自定义登录页面
//                .loginPage("/login.html")
                // 点击提交表单后的跳转页
//                .loginProcessingUrl("/user/login")
//                .successHandler(new MyAuthenticationSuccessHandler())
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                // 请求授权
                .authorizeRequests()
                // login中发送post请求，所以使用permitAll来授权
//                .antMatchers("/login.html").permitAll()
                .antMatchers("/user/login").anonymous()
//                .antMatchers("/user/in").permitAll()
                // 所有请求
                .anyRequest()
                // 都需要身份认证
                .authenticated()
                .and()
                //关闭跨域请求
                .csrf().disable();

        http.formLogin().permitAll();
    }

}
