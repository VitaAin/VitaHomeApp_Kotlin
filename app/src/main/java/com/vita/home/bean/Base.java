package com.vita.home.bean;

/**
 * @FileName: com.vita.home.bean.Base.java
 * @Author: Vita
 * @Date: 2018-01-09 14:27
 * @Usage:
 */
abstract class Base {

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
