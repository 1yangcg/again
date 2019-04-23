package com.example.henannews_2.main;


import com.example.henannews_2.base.BaseModel;
import com.example.henannews_2.main.bean.AvatarBean;
import com.example.henannews_2.main.bean.NewsCategory;
import com.example.henannews_2.main.callback.MainCallback;
import com.example.henannews_2.main.callback.NewsCategoryCallback;
import com.example.henannews_2.main.service.MainService;
import com.example.henannews_2.utils.NewsConstants;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {


    public void upload(String path, final MainCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sm.ms/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MainService service = retrofit.create(MainService.class);
        MediaType formType = MediaType.parse("multipart/form-data");
        MediaType fileType = MediaType.parse("application/otcet-stream");
        RequestBody format = RequestBody.create(formType, "json");
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(fileType, file);
        String name = null;
        try {
            name = URLEncoder.encode(file.getName(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("smfile", name, fileBody);
        Call<AvatarBean> call = service.uploadImage(format, filePart);
        call.enqueue(new Callback<AvatarBean>() {
            @Override
            public void onResponse(Call<AvatarBean> call, Response<AvatarBean> response) {
                if (callback == null) {
                    return;
                }
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(response.message());
                }

            }

            @Override
            public void onFailure(Call<AvatarBean> call, Throwable t) {
                if (callback == null) {
                    return;
                }
                callback.onFailure(t.getMessage());
            }
        });
    }


    /**
     * 请求新闻分类数据
     */
    public void getNewsCategory(final NewsCategoryCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsConstants.NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MainService service = retrofit.create(MainService.class);
        Call<NewsCategory> call = service.getNewsCategory();
        call.enqueue(new Callback<NewsCategory>() {
            @Override
            public void onResponse(Call<NewsCategory> call, Response<NewsCategory> response) {
                if (callback != null) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFailure(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsCategory> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(t.getMessage());
                }
            }
        });
    }

}
