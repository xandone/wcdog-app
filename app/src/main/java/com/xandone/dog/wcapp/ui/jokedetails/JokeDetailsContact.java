package com.xandone.dog.wcapp.ui.jokedetails;

import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.JokeBean;

import java.util.List;


/**
 * author: xandone
 * created on: 2019/6/15 16:35
 */

public interface JokeDetailsContact {
    interface View extends BaseView {
        void showContent(BaseResponse baseResponse);

        void thumbsJokeResult(BaseResponse baseResponse);

        void showCollectionResult(boolean success, String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getThumbsJoke(String jokeId, String jokeUserId);

        void thumbsJoke(String jokeId, String jokeUserId);

        void addToCollection(JokeBean jokeBean);
    }
}
