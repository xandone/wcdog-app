package com.xandone.dog.wcapp.model;

import com.xandone.dog.wcapp.api.http.HttpHelper;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.model.video.VideoInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * author: xandone
 * created on: 2019/3/6 8:53
 */

public class DataManager implements HttpHelper {
    HttpHelper mHttpHelper;

    public DataManager(HttpHelper httpHelper) {
        this.mHttpHelper = httpHelper;
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> login(String name, String psw) {
        return mHttpHelper.login(name, psw);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getJokeList(int page, int count,String tag) {
        return mHttpHelper.getJokeList(page, count,tag);
    }

    @Override
    public Flowable<VideoInfo> getVideoList(Map<String, String> map) {
        return mHttpHelper.getVideoList(map);
    }


}
