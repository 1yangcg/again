package com.example.henannews_2.base;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends FragmentActivity {

    protected Context context;
    private Unbinder bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        bind = ButterKnife.bind(this);
        context = getApplicationContext();

    }



    protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
