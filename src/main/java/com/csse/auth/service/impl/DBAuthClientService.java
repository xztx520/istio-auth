package com.csse.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csse.auth.bean.ClientInfo;
import com.csse.auth.entity.Client;
import com.csse.auth.mapper.ClientMapper;
import com.csse.auth.service.AuthClientService;
import com.csse.auth.utils.AESUtil;
import com.csse.auth.utils.ClientTokenUtil;
import com.csse.common.exception.PlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
@RefreshScope
@Service
public class DBAuthClientService implements AuthClientService {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Value("${secret_key}")
    private String secret_key;
    @Value("${is_secret_key}")
    private boolean is_secret_key;

    @Override
    public String apply(String clientId, String secret) throws PlatformException {
        Client client = getClient(clientId, secret);
        return clientTokenUtil.generateToken(new ClientInfo(client.getClientId(), client.getEnglishName(), client.getClientId()));
    }

    @Override
    public void validateclient(String token) throws PlatformException {
        clientTokenUtil.getInfoFromToken(token);
    }

    private Client getClient(String clientId, String secret) throws PlatformException {
        LambdaQueryWrapper<Client> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Client::getClientId, clientId);
        Client client = clientMapper.selectOne(lambdaQueryWrapper);
        String SecretKey = client.getSecretKey();
        if (is_secret_key) {
            SecretKey = AESUtil.dcodes(client.getSecretKey(), secret_key);
            client.setSecretKey(SecretKey);
        }
        if (client == null || !SecretKey.equals(secret)) {
            throw new PlatformException("301140", "Client not found or Client secret is error!");
        }
        return client;
    }

}
