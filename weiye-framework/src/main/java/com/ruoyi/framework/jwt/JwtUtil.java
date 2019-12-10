package com.ruoyi.framework.jwt;
 
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruoyi.framework.web.exception.user.UserNotExistsException;
import com.ruoyi.framework.web.util.ServletUtils;

import java.util.Date;
 
/**
 * @author Mr.Li
 * @create 2018-07-12 14:23
 * @desc JWT工具类
 **/
public class JwtUtil {
 
    private static final long EXPIRE_TIME = 10 * 60 * 1000;
 
    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
 
    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getLoginName() {
        try {
            String token = ServletUtils.getRequest().getHeader("Authorization");
            if(StrUtil.isBlank( token )){
                throw new UserNotExistsException();
            }
            DecodedJWT jwt = JWT.decode(token);
            String loginName = jwt.getClaim( "loginName" ).asString();
//            jwt.getExpiresAt();
            if (StrUtil.isBlank( loginName )) {
                throw new UserNotExistsException();
            }
            return loginName;
        } catch (JWTDecodeException e) {
            throw new UserNotExistsException();
        }
    }
 
    /**
     * 生成签名,10min后过期
     *
     * @param loginName 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String loginName, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("loginName", loginName)
                .withExpiresAt(date)
                .sign(algorithm);
 
    }
}