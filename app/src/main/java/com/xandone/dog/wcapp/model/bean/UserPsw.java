package com.xandone.dog.wcapp.model.bean;

/**
 * author: xandone
 * created on: 2018/3/12 14:23
 */

public class UserPsw {
    private String name;
    private String psw;

    public UserPsw(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
