package com.csse.auth.utils;

import com.csse.auth.constant.CommonConstants;
import com.csse.common.exception.PlatformException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * @description:
 * @company: 喝咖啡的大象
 * @author: 施海洲
 * @date: Created in 2020/7/1 0008 上午 11:28
 */
public class JWTHelper {
    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKey
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(IJWTInfo jwtInfo, byte priKey[], int expire) {
        String compactJws = null;
        //修改同时要修改ServiceRoleBind
        //String k = StringUtils.replace(jwtInfo.getUniqueName(), ".", "-");
        String k = StringUtils.replace(StringUtils.replace(jwtInfo.getUniqueName(), ".", "-"),"_","-");

        try {
            compactJws = Jwts.builder()
                    .setIssuer("csse")
                    .setSubject(jwtInfo.getUniqueName())
                    .claim(CommonConstants.JWT_KEY_APP_ID, jwtInfo.getUniqueName())
                    .claim(CommonConstants.JWT_KEY_NAME, k)
                    .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                    .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compactJws;
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKey) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static IJWTInfo getInfoFromToken(String token, byte[] pubKey) throws PlatformException {
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = parserToken(token, pubKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PlatformException("401", "非法token");
        }
        Claims body = claimsJws.getBody();
        return new JWTInfo(body.getSubject(), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_APP_ID)), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)));
    }
}
