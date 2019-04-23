package com.example.henannews_2.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    /**
     * 从SharedPreferences获取boolean值
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        Context context = ContextHelper.getContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        boolean value = preferences.getBoolean(key, defaultValue);
        return value;
    }

    /**
     * 调用getBoolean(String key, boolean defaultValue)
     * defaultValue为false
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }


    public static boolean putBoolean(String key, boolean value) {
        Context context = ContextHelper.getContext();
        SharedPreferences preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return preferences.edit().putBoolean(key, value).commit();
    }

}
