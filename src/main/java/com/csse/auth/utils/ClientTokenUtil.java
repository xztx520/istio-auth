package com.csse.auth.utils;

import com.csse.auth.configuration.KeyConfiguration;
import com.csse.common.exception.PlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
@Configuration
public class ClientTokenUtil {

    @Value("${client.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    public String generateToken(IJWTInfo jwtInfo) {
        return JWTHelper.generateToken(jwtInfo, keyConfiguration.getServicePriKey(), expire);
}

    public IJWTInfo getInfoFromToken(String token) throws PlatformException {
        return JWTHelper.getInfoFromToken(token, keyConfiguration.getServicePubKey());
    }

}
