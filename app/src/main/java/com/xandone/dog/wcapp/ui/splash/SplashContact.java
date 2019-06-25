package com.xandone.dog.wcapp.ui.splash;

import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;

import java.util.List;


/**
 * author: xandone
 * created on: 2019/6/12 9:16
 */

public interface SplashContact {

    interface  View extends BaseView {
        void showContent(BaseResponse<List<UserBean>> baseResponse);
        void jumpAct();
    }

    interface Presenter extends BasePresenter<View> {
        void getContent(String name, String psw);
    }


}
