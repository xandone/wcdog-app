package com.xandone.dog.wcapp.ui.login;

import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;

import java.util.List;


/**
 * author: xandone
 * created on: 2019/3/7 14:08
 */

public interface LoginContact {

    interface View extends BaseView {
        void showContent(BaseResponse<List<UserBean>> baseResponse);
    }

    interface Presenter extends BasePresenter<View> {

        void login(String email, String psw);
    }
}
