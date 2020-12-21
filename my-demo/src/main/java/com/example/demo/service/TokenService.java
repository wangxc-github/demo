package com.example.demo.service;

import com.example.demo.entity.User;


/**
 * @author wangxc
 */
public interface TokenService {

    /**
     * 获取token
     * @param user
     * @return
     */
     String getToken(User user);


}
