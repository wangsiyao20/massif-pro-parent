package com.massif.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.massif")  // 被依赖那个模块中所有Bean对象都要扫描才能找到
@MapperScan("com.massif.system.mapper")
public class OAuth2Application {


    public static void main(String[] args) {
        SpringApplication.run(OAuth2Application.class, args);
    }

}
