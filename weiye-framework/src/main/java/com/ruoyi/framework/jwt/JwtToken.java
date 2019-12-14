package com.ruoyi.framework.jwt;
 
import org.apache.shiro.authc.AuthenticationToken;
 
/**
 * @author Mr.Li
 * @create 2018-07-12 15:19
 * @desc
 **/
public class JwtToken implements AuthenticationToken {
 
    private String token;
 
    public JwtToken(String token) {
        this.token = token;
    }
 
    @Override
    public Object getPrincipal() {
        return token;
    }
 
    @Override
    public Object getCredentials() {
        return token;
    }
}