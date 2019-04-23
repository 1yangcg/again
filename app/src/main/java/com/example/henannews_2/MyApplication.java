package com.example.henannews_2;


import android.app.Application;

import com.example.henannews_2.utils.ContextHelper;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHelper.setContext(this);
    }
}
