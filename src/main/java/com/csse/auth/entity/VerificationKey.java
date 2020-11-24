package com.csse.auth.entity;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
public class VerificationKey {
    /**
     * 公钥ID
     */
    private String kid;
    /**
     * 公钥算法类型
     */
    private String kty;
    /**
     * 公钥算法
     */
    private String alg;
    /**
     * 公钥用途:  sig 签名;enc 加密
     */
    private String use;
    /**
     * 公钥
     */
    private String n;
    /**
     * AQAB
     */
    private String e;

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getKty() {
        return kty;
    }

    public void setKty(String kty) {
        this.kty = kty;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}