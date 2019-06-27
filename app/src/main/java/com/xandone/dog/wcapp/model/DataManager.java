package com.xandone.dog.wcapp.model;

import com.xandone.dog.wcapp.api.http.HttpHelper;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.CommentBean;
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
    public Flowable<BaseResponse<List<UserBean>>> regist(String name, String psw, String nickname) {
        return mHttpHelper.regist(name, psw, nickname);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getJokeList(int page, int count, String tag) {
        return mHttpHelper.getJokeList(page, count, tag);
    }

    @Override
    public Flowable<VideoInfo> getVideoList(Map<String, String> map) {
        return mHttpHelper.getVideoList(map);
    }

    @Override
    public Flowable<BaseResponse> getThumbsJoke(String jokeId, String jokeUserId) {
        return mHttpHelper.getThumbsJoke(jokeId, jokeUserId);
    }

    @Override
    public Flowable<BaseResponse> thumbsJoke(String jokeId, String jokeUserId) {
        return mHttpHelper.thumbsJoke(jokeId, jokeUserId);
    }

    @Override
    public Flowable<BaseResponse<List<CommentBean>>> getJokeCommentList(int page, int rows, String jokeId) {
        return mHttpHelper.getJokeCommentList(page, rows, jokeId);
    }

    @Override
    public Flowable<BaseResponse<List<CommentBean>>> addComment(String jokeId, String userId, String details) {
        return mHttpHelper.addComment(jokeId, userId, details);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getSelfJokes(int page, int row, String userId) {
        return mHttpHelper.getSelfJokes(page, row, userId);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> getSelfThumbs(int page, int row, String userId) {
        return mHttpHelper.getSelfThumbs(page, row, userId);
    }

    @Override
    public Flowable<BaseResponse<List<JokeBean>>> dealSearchJokes(int page, int row, String key) {
        return mHttpHelper.dealSearchJokes(page, row, key);
    }

    @Override
    public Flowable<BaseResponse<List<UserBean>>> updateUserInfo(String userId, String talk, String address) {
        return mHttpHelper.updateUserInfo(userId, talk, address);
    }


}
