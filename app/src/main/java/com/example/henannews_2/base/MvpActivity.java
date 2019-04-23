package com.example.henannews_2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public abstract class MvpActivity<V extends BaseView, P extends BasePresenter<V>, D> extends BaseActivity implements BaseView<D> {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
        initView();
        initData();
    }

    protected abstract P createPresenter();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
