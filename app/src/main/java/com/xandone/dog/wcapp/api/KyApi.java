package com.xandone.dog.wcapp.api;

import com.xandone.dog.wcapp.model.video.VideoInfo;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * author: xandone
 * created on: 2019/4/19 22:08
 */
public interface KyApi {

    String HOST = "http://baobab.wandoujia.com/";

    @GET("api/v3/ranklist")
    Flowable<VideoInfo> getVideoList(@QueryMap Map<String, String> map);

}
