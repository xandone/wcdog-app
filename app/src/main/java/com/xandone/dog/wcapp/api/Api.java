package com.xandone.dog.wcapp.api;

import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.model.bean.UserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * author: xandone
 * created on: 2019/3/6 9:23
 */

public interface Api {
    //        String HOST = "http://192.168.191.1:8080/";
    String HOST = "http://192.168.191.1:8081/";

    @FormUrlEncoded
    @POST("wcdog/user/regist")
    Flowable<BaseResponse<List<UserBean>>> regist(
            @Field("name") String name,
            @Field("password") String psw,
            @Field("nickname") String nick);

    //    @Headers("Cache-Control:public,max-age=0")
    @FormUrlEncoded
    @POST("wcdog/user/login")
    Flowable<BaseResponse<List<UserBean>>> login(
            @Field("name") String name,
            @Field("psw") String psw);

    @GET("wcdog/joke/jokelist")
    Flowable<BaseResponse<List<JokeBean>>> getJokeList(
            @Query("page") int page,
            @Query("row") int count,
            @Query("tag") String tag);
}
