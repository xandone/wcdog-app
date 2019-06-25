package com.xandone.dog.wcapp.ui.regist;

import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;

import java.util.List;


/**
 * author: xandone
 * created on: 2018/3/6 14:47
 */

public interface RegistContact {

    interface View extends BaseView {
        void showContent(BaseResponse<List<UserBean>> baseResponse);
    }

    interface Presenter extends BasePresenter<View> {

        void regist(String name, String psw, String nick);
    }
}
