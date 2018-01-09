package com.vita.home.bean;

/**
 * @FileName: com.vita.home.bean.Wrap.java
 * @Author: Vita
 * @Date: 2018-01-09 14:27
 * @Usage:
 */
public class Wrap<T> {

    private int status;
    private String message;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
