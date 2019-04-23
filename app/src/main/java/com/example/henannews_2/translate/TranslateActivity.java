package com.example.henannews_2.translate;


import android.os.Bundle;
import android.widget.Toast;

import com.example.henannews_2.R;
import com.example.henannews_2.base.MvpActivity;

/**
 * 1. Activity持有一个Presenter对象，可以调用Presenter中的方法；
 * 2. Activity实现了View接口，用于更新UI，是Presenter和View表演的舞台
 */
public class TranslateActivity extends MvpActivity<TranslateView, TranslatePresenter, WordBean> implements TranslateView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.translate("json", "auto", "code");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_translate;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected TranslatePresenter createPresenter() {
        return new TranslatePresenter();
    }

    @Override
    public void onSuccess(WordBean data) {
        String result = data.getTranslateResult().get(0).get(0).getTgt();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        System.out.println(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
