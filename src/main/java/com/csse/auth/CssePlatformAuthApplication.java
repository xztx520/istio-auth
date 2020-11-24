package com.csse.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
@SpringBootApplication
//@RefreshScope
public class CssePlatformAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CssePlatformAuthApplication.class, args);
    }

}
