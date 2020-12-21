package com.example.demo.entity;

import lombok.Data;

/**
 * @author wangxc
 */
@Data
public class User {

    private String id;

    private String name;

    private String username;

    private String email;

    private String password;

    private String phoneNo;
    /**
     * 注册时间
     */
    private String signUpDate;
}
