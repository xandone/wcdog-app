package com.xandone.dog.wcapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: xandone
 * created on: 2018/3/7 10:23
 */

public class UserBean implements Serializable {

    /**
     * name : 1@qq.com
     * password : 1
     * nickname : 狗蛋
     * userId : 1
     * userIcon : http://p1.pstatp.com/large/pgc-image/e0c76ffcfb564a3ebc1cb2bab20c0028
     * talk : 到底谁都披萨盘底盘丝灯泡如果如果步123344
     * address : 湖北-武汉123
     * token : null
     * registTime : 2019-01-16 19:21:30
     * lastLoginTime : 2019-06-24 16:49:26
     */

    private String name;
    private String password;
    private String nickname;
    private String userId;
    private String userIcon;
    private String talk;
    private String address;
    private Object token;
    private String registTime;
    private String lastLoginTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

}
