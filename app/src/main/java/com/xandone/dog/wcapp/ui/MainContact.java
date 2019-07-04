package com.xandone.dog.wcapp.ui;

import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.bean.ApkBean;

/**
 * author: xandone
 * created on: 2019/7/3 14:21
 * description:
 */
public interface MainContact {
    interface MyView extends BaseView {
        void showResult(ApkBean apkBean);
    }

    interface Presenter extends BasePresenter<MyView> {
        void getLastVersion();
    }
}
