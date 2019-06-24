package com.xandone.dog.wcapp.ui.personal;


import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

/**
 * author: xandone
 * Created on: 2019/5/9 17:12
 */

public interface PersonalContact {
    interface MyView extends BaseView {
        void showContent(BaseResponse<List<UserBean>> baseResponse);

    }

    interface Presenter extends BasePresenter<MyView> {

        void changeUserIcon(Map<String, String> maps, MultipartBody.Part part);

    }
}
