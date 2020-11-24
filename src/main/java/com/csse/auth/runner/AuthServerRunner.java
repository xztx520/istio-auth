package com.csse.auth.runner;

import com.csse.auth.configuration.KeyConfiguration;
import com.csse.auth.utils.RsaKeyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
@Configuration
public class AuthServerRunner implements CommandLineRunner {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String REDIS_SERVICE_PRI_KEY = "CSSE:AUTH:CLIENT:PRI";
    private static final String REDIS_SERVICE_PUB_KEY = "CSSE:AUTH:CLIENT:PUB";
    private static final String REDIS_SERVICE_JWK_KEY = "CSSE:AUTH:CLIENT:JWK";

    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... args) throws Exception {
        if (redisTemplate.hasKey(REDIS_SERVICE_PRI_KEY) && redisTemplate.hasKey(REDIS_SERVICE_PUB_KEY) && redisTemplate.hasKey(REDIS_SERVICE_JWK_KEY)) {
            keyConfiguration.setServicePriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PRI_KEY)));
            keyConfiguration.setServicePubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PUB_KEY)));
            keyConfiguration.setJwk(redisTemplate.opsForValue().get(REDIS_SERVICE_JWK_KEY));
        } else {
            Map<String, String> keyMap = RsaKeyHelper.generateKey();
            keyConfiguration.setServicePriKey(RsaKeyHelper.toBytes(keyMap.get("pri")));
            keyConfiguration.setServicePubKey(RsaKeyHelper.toBytes(keyMap.get("pub")));
            keyConfiguration.setJwk(keyMap.get("jwk"));
            redisTemplate.opsForValue().set(REDIS_SERVICE_PRI_KEY, keyMap.get("pri"));
            redisTemplate.opsForValue().set(REDIS_SERVICE_PUB_KEY, keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_SERVICE_JWK_KEY, keyMap.get("jwk"));
        }
    }
}
