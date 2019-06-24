package com.xandone.dog.wcapp.api.http;


import com.xandone.dog.wcapp.api.Api;
import com.xandone.dog.wcapp.api.KyApi;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.model.video.VideoInfo;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;

/**
 * author: xandone
 * created on: 2019/3/6 8:53
 */

public class RetrofitHelper implements HttpHelper {
    private Api mApi;
    private KyApi mKyApi;


    @Inject
    public RetrofitHelper(Api api, KyApi kyApi) {
        this.mApi = api;
        this.mKyApi = kyApi;
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> login(String name, String psw) {
        return mApi.login(name, psw);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getJokeList(int page, int count) {
        return mApi.getJokeList(page, count, -1);
    }

    @Override
    public Flowable<VideoInfo> getVideoList(Map<String, String> map) {
        return mKyApi.getVideoList(map);
    }

}
