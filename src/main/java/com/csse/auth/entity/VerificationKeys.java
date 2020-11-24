package com.csse.auth.entity;

import java.util.List;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
public class VerificationKeys {
    public VerificationKeys() {
    }

    public VerificationKeys(List<VerificationKey> keys) {
        this.keys = keys;
    }

    private List<VerificationKey> keys;

    public List<VerificationKey> getKeys() {
        return keys;
    }

    public void setKeys(List<VerificationKey> keys) {
        this.keys = keys;
    }
}

