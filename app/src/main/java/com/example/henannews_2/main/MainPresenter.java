package com.example.henannews_2.main;


import com.example.henannews_2.base.BasePresenter;
import com.example.henannews_2.base.ModelManager;
import com.example.henannews_2.main.bean.AvatarBean;
import com.example.henannews_2.main.bean.NewsCategory;
import com.example.henannews_2.main.callback.MainCallback;
import com.example.henannews_2.main.callback.NewsCategoryCallback;

public class MainPresenter extends BasePresenter<MainView> {

    public void upload(String path) {
        MainModel model = ModelManager.getInstance().getModel(MainModel.class);
        model.upload(path, new MainCallback() {
            @Override
            public void onSuccess(AvatarBean data) {
                if (isAttach()) {
                    view.onSuccess(data);
                }
            }

            @Override
            public void onFailure(String message) {
                if (isAttach()) {
                    view.onFailure(message);
                }
            }
        });

    }


    public void getNewsCategory() {
        MainModel model = ModelManager.getInstance().getModel(MainModel.class);
        model.getNewsCategory(new NewsCategoryCallback() {
            @Override
            public void onSuccess(NewsCategory data) {
                if (isAttach()) {
                    view.onNewsCategory(data);
                }
            }

            @Override
            public void onFailure(String message) {
                if (isAttach()) {
                    view.onFailure(message);
                }
            }
        });
    }
}
