package com.xandone.dog.wcapp.ui.video;


import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.video.VideoInfo;

import java.util.Map;

/**
 * author: xandone
 * created on: 2019/4/19 19:10
 */
public interface VideoContact {
    interface MyView extends BaseView {
        void showContent(VideoInfo videoInfo);
    }

    interface Presenter extends BasePresenter<MyView> {

        void getVideoList(Map<String, String> value);
    }
}
