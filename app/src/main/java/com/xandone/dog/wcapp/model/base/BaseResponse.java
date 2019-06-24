package com.xandone.dog.wcapp.model.base;

/**
 * author: xandone
 * created on: 2019/3/6 8:57
 */

public class BaseResponse<T> {

    /**
     * code : 1
     * msg : 注册成功
     * data : null
     */

    private int code;
    private String msg;
    private T data;
    private int total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
