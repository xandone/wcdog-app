package com.xandone.dog.wcapp.api;

import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.CommentBean;
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
    String HOST = "http://192.168.191.1:8081/wcdog/";

    @FormUrlEncoded
    @POST("user/regist")
    Flowable<BaseResponse<List<UserBean>>> regist(
            @Field("name") String name,
            @Field("psw") String psw,
            @Field("nickname") String nickname);

    //    @Headers("Cache-Control:public,max-age=0")
    @FormUrlEncoded
    @POST("user/login")
    Flowable<BaseResponse<List<UserBean>>> login(
            @Field("name") String name,
            @Field("psw") String psw);

    @GET("joke/jokelist")
    Flowable<BaseResponse<List<JokeBean>>> getJokeList(
            @Query("page") int page,
            @Query("row") int count,
            @Query("tag") String tag);

    @GET("joke/joke/thumbs/self")
    Flowable<BaseResponse> getThumbsJoke(
            @Query("jokeId") String jokeId,
            @Query("jokeUserId") String jokeUserId);

    @GET("joke/joke/thumbs")
    Flowable<BaseResponse> thumbsJoke(
            @Query("jokeId") String jokeId,
            @Query("jokeUserId") String jokeUserId);

    @GET("joke/comment/list")
    Flowable<BaseResponse<List<CommentBean>>> getJokeCommentList(
            @Query("page") int page,
            @Query("row") int rows,
            @Query("jokeId") String jokeId
    );

    @FormUrlEncoded
    @POST("joke/comment/add")
    Flowable<BaseResponse<List<CommentBean>>> addComment(
            @Field("jokeId") String jokeId,
            @Field("userId") String userId,
            @Field("details") String details
    );

    @FormUrlEncoded
    @POST("user/userInfo/modify")
    Flowable<BaseResponse<List<UserBean>>> updateUserInfo(
            @Field("userId") String userId,
            @Field("talk") String talk,
            @Field("address") String address
    );

    @GET("user/selfJokes")
    Flowable<BaseResponse<List<JokeBean>>> getSelfJokes(
            @Query("page") int page,
            @Query("row") int row,
            @Query("userId") String userId
    );

    @GET("user/thumb")
    Flowable<BaseResponse<List<JokeBean>>> getSelfThumbs(
            @Query("page") int page,
            @Query("row") int row,
            @Query("userId") String userId
    );

    @GET("joke/jokelist/foggy")
    Flowable<BaseResponse<List<JokeBean>>> dealSearchJokes(
            @Query("page") int page,
            @Query("row") int row,
            @Query("key") String key
    );

}
