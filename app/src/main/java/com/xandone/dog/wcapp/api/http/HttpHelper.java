package com.xandone.dog.wcapp.api.http;


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

public interface HttpHelper {

    Flowable<BaseResponse<List<UserBean>>> login(String name, String psw);

    Flowable<BaseResponse<List<JokeBean>>> getJokeList(int page, int count,String tag);

    Flowable<VideoInfo> getVideoList(Map<String, String> map);

}
