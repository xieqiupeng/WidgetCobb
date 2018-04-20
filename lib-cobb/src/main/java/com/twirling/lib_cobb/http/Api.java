package com.twirling.lib_cobb.http;

import com.alibaba.fastjson.JSONObject;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 2016/01/13 10:10:37
 *
 * @author qiupengxie
 */
public interface Api {

    @FormUrlEncoded
    @POST("/")
    Observable<JSONObject> doSomething(@Field("id") String id);


}