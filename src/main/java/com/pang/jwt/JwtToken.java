package com.pang.jwt;

import com.pang.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken:实现shiro的AuthenticationToken接口的类JwtToken
 *
 * @author pangjian
 * @date: 2020/10/16
 */
public class JwtToken implements AuthenticationToken{

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return JwtUtil.getUsername(token);
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
