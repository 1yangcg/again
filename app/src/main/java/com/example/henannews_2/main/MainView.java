package com.example.henannews_2.main;


import com.example.henannews_2.base.BaseView;
import com.example.henannews_2.main.bean.AvatarBean;
import com.example.henannews_2.main.bean.NewsCategory;

public interface MainView extends BaseView<AvatarBean> {

    void onNewsCategory(NewsCategory category);


}
