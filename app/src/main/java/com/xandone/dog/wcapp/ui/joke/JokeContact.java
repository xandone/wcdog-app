package com.xandone.dog.wcapp.ui.joke;

import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.bean.JokeBean;

import java.util.List;

/**
 * author: xandone
 * created on: 2018/3/13 9:27
 */

public interface JokeContact {
    int MODE_ONE = 0;
    int MODE_MORE = 1;

    interface View extends BaseView {

        void showContent(List<JokeBean> jokeList, int total);

        void showContentMore(List<JokeBean> jokeList, int total);
    }

    interface Presenter extends BasePresenter<View> {
        void getJokeList(int page, int count, String tag, int mode);
    }

}
