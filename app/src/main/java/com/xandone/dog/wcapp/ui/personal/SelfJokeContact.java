package com.xandone.dog.wcapp.ui.personal;


import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.bean.JokeBean;

import java.util.List;


/**
 * author: xandone
 * Created on: 2019/6/24 17:12
 */

public interface SelfJokeContact {
    int MODE_ONE = 0;
    int MODE_MORE = 1;

    interface MyView extends BaseView {

        void showContent(List<JokeBean> jokeList, int total);

        void showContentMore(List<JokeBean> jokeList, int total);
    }

    interface Presenter extends BasePresenter<MyView> {

        void getSelfJokes(int page, int count, String tag, int mode);

        void getSelfThumbs(int page, int count, String tag, int mode);

    }
}
