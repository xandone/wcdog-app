package com.xandone.dog.wcapp.ui.personal;


import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.base.BaseResponse;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * author: xandone
 * Created on: 2019/5/9 17:12
 */

public interface PersonalContact {
    interface MyView extends BaseView {
        void showContent(BaseResponse response);

    }

    interface Presenter extends BasePresenter<MyView> {

        void changeUserIcon(Map<String, String> maps, MultipartBody.Part part);

        void updateUserInfo(String talk, String address);

    }
}
