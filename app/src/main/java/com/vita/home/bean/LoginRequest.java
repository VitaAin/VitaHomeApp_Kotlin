package com.vita.home.bean;

/**
 * @FileName: com.vita.home.bean.LoginRequest.java
 * @Author: Vita
 * @Date: 2018-01-10 14:15
 * @Usage:
 */
public class LoginRequest {

    private String account;
    private String password;

    public LoginRequest(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
