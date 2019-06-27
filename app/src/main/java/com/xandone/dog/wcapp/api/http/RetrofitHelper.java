package com.xandone.dog.wcapp.api.http;


import com.xandone.dog.wcapp.api.Api;
import com.xandone.dog.wcapp.api.KyApi;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.CommentBean;
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
    public Flowable<BaseResponse<List<UserBean>>> regist(String name, String psw, String nickname) {
        return mApi.regist(name, psw, nickname);
    }


    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getJokeList(int page, int count, String tag) {
        return mApi.getJokeList(page, count, tag);
    }

    @Override
    public Flowable<VideoInfo> getVideoList(Map<String, String> map) {
        return mKyApi.getVideoList(map);
    }

    @Override
    public Flowable<BaseResponse> getThumbsJoke(String jokeId, String jokeUserId) {
        return mApi.getThumbsJoke(jokeId, jokeUserId);
    }

    @Override
    public Flowable<BaseResponse> thumbsJoke(String jokeId, String jokeUserId) {
        return mApi.thumbsJoke(jokeId, jokeUserId);
    }

    @Override
    public Flowable<BaseResponse<List<CommentBean>>> getJokeCommentList(int page, int row, String jokeId) {
        return mApi.getJokeCommentList(page, row, jokeId);
    }

    @Override
    public Flowable<BaseResponse<List<CommentBean>>> addComment(String jokeId, String userId, String details) {
        return mApi.addComment(jokeId, userId, details);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getSelfJokes(int page, int row, String userId) {
        return mApi.getSelfJokes(page, row, userId);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getSelfThumbs(int page, int row, String userId) {
        return mApi.getSelfThumbs(page, row, userId);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> dealSearchJokes(int page, int row, String key) {
        return mApi.dealSearchJokes(page, row, key);
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> updateUserInfo(String userId, String talk, String address) {
        return mApi.updateUserInfo(userId, talk, address);
    }

}
