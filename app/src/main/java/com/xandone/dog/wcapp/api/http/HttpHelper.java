package com.xandone.dog.wcapp.api.http;


import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.ApkBean;
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

public interface HttpHelper {

    Flowable<BaseResponse<List<UserBean>>> login(String name, String psw);

    Flowable<BaseResponse<List<UserBean>>> regist(String name, String psw, String nickname);

    Flowable<BaseResponse<List<JokeBean>>> getJokeList(int page, int count, String tag);

    Flowable<VideoInfo> getVideoList(Map<String, String> map);

    Flowable<BaseResponse> getThumbsJoke(String jokeId, String jokeUserId);

    Flowable<BaseResponse> thumbsJoke(String jokeId, String jokeUserId);

    Flowable<BaseResponse<List<CommentBean>>> getJokeCommentList(int page, int rows, String jokeId);

    Flowable<BaseResponse<List<CommentBean>>> addComment(String jokeId, String userId, String details);

    Flowable<BaseResponse<List<JokeBean>>> getSelfJokes(int page, int row, String userId);

    Flowable<BaseResponse<List<JokeBean>>> getSelfThumbs(int page, int row, String userId);

    Flowable<BaseResponse<List<JokeBean>>> dealSearchJokes(int page, int row, String key);

    Flowable<BaseResponse<List<UserBean>>> updateUserInfo(String userId, String talk, String address);

    Flowable<BaseResponse<List<ApkBean>>> getLastVersion();


}
