package com.xandone.dog.wcapp.cache;

import com.xandone.dog.wcapp.model.bean.UserBean;


/**
 * author: xandone
 * created on: 2019/5/7 10:22
 */

public class UserInfoCache {

    private static boolean mLogin;
    private static UserBean mUserBean;

    public static boolean isLogin() {
        return mLogin;
    }

    public static void setLogin(boolean login) {
        UserInfoCache.mLogin = login;
    }

    public static UserBean getUserBean() {
        return mUserBean;
    }

    public static void setUserBean(UserBean userBean) {
        UserInfoCache.mUserBean = userBean;
    }
}
