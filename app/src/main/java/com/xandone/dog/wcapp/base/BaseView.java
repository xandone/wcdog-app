package com.xandone.dog.wcapp.base;

/**
 * author: xandone
 * created on: 2019/3/5 15:40
 */

public interface BaseView {
    void showMsg(String msg, int loadStatus);

    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
