package com.xandone.dog.wcapp.base;

/**
 * author: xandone
 * created on: 2019/3/5 15:40
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
