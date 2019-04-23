package com.example.henannews_2.translate;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TranslateService {

    @GET("translate?")
    Call<WordBean> translate(@Query("doctype") String doctype, @Query("type") String type, @Query("i") String i);

}
