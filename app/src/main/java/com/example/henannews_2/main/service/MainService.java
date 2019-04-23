package com.example.henannews_2.main.service;


import com.example.henannews_2.main.bean.AvatarBean;
import com.example.henannews_2.main.bean.NewsCategory;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MainService {

    @Multipart
    @Headers({"User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0"
            , "Connection: keep-alive"})
    @POST("https://sm.ms/api/upload")
    Call<AvatarBean> uploadImage(@Part("format") RequestBody format, @Part MultipartBody.Part file);


    @GET("categories.json")
    Call<NewsCategory> getNewsCategory();
}
