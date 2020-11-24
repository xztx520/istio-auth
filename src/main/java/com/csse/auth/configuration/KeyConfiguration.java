package com.csse.auth.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
@Configuration
@Data
public class KeyConfiguration {

    private String jwk;

    private byte[] servicePriKey;

    private byte[] servicePubKey;

}
