package com.csse.auth.service;


import com.csse.common.exception.PlatformException;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
public interface AuthClientService {
    /**
     * 获取token
     * @param clientId
     * @param secret
     * @return
     * @throws PlatformException
     */
    String apply(String clientId, String secret) throws PlatformException;

    /**
     * 校验token
     * @param token
     * @throws PlatformException
     */
    void validateclient(String token) throws PlatformException;

}
