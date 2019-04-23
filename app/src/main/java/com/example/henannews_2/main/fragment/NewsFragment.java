package com.example.henannews_2.main.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.henannews_2.R;
import com.example.henannews_2.base.BaseFragment;
import com.example.henannews_2.main.MainActivity;
import com.example.henannews_2.main.bean.NewsCategory;
import com.example.henannews_2.main.news.NewsPagerAdapter;

import java.util.List;

import butterknife.BindView;

public class NewsFragment extends BaseFragment {

    @BindView(R.id.newsViewPager)
    ViewPager newsViewPager;

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        List<NewsCategory.DataBean.ChildrenBean> children = activity.getChildren();
        NewsPagerAdapter adapter = new NewsPagerAdapter(children);
        newsViewPager.setAdapter(adapter);
    }
}
