package com.example.henannews_2.base;

public interface BaseView<D> {

    void onSuccess(D data);

    void onFailure(String message);

}
