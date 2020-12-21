package com.example.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.User;
import com.example.demo.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * @author wangxc
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
