package com.csse.auth.controller;

import com.alibaba.fastjson.JSON;
import com.csse.auth.configuration.KeyConfiguration;
import com.csse.auth.entity.VerificationKey;
import com.csse.auth.entity.VerificationKeys;
import com.csse.auth.service.AuthClientService;
import com.csse.common.base.R;
import com.csse.common.exception.PlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private KeyConfiguration keyConfiguration;
    @Autowired
    private AuthClientService authClientService;

    @RequestMapping(value = "/token_keys", method = {RequestMethod.GET, RequestMethod.POST})
    public VerificationKeys tokenKeys() {
        VerificationKeys keys = new VerificationKeys();
        String pubKeyJson = keyConfiguration.getJwk();
        List<VerificationKey> keyList = new ArrayList<VerificationKey>();
        VerificationKey key = JSON.parseObject(pubKeyJson, VerificationKey.class);
        keyList.add(key);
        keys.setKeys(keyList);
        return keys;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public R getAccessToken(String clientId, String secret) {
        R r;
        try {
            String token = authClientService.apply(clientId, secret);
            r = new R(token);
        } catch (PlatformException e) {
            e.printStackTrace();
            r = new R(e.getErrorCode(), e.getMessage());
        }
        return r;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public R verify(String token) {
        R r;
        try {
            authClientService.validateclient(token);
            r = new R();
        } catch (PlatformException e) {
            e.printStackTrace();
            r = new R(e.getErrorCode(), e.getMessage());
        }
        return r;

    }

}