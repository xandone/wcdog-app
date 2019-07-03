package com.xandone.dog.wcapp.ui.video;



import com.xandone.dog.wcapp.api.CommonSubscriber;
import com.xandone.dog.wcapp.base.RxPresenter;
import com.xandone.dog.wcapp.model.DataManager;
import com.xandone.dog.wcapp.model.video.VideoInfo;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2019/4/19 19:09
 */
public class VideoListPresenter extends RxPresenter<VideoContact.MyView> implements VideoContact.Presenter {

    private DataManager mDataManager;

    @Inject
    VideoListPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }


    @Override
    public void getVideoList(Map<String, String> map) {
        Flowable<VideoInfo> result = mDataManager.getVideoList(map);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<VideoInfo>(view) {
                    @Override
                    public void onSuccess(VideoInfo videoInfo) {
                        view.showContent(videoInfo);
                    }

                })
        );
    }
}
