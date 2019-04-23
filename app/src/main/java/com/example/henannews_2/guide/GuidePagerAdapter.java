package com.example.henannews_2.guide;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.henannews_2.R;

import java.util.ArrayList;
import java.util.List;

public class GuidePagerAdapter extends PagerAdapter {

    private List<Integer> guideResIdList = new ArrayList<>();

    private View.OnClickListener guideButtonListener;

    public GuidePagerAdapter() {
        // 初始化引导页资源ID集合
        guideResIdList.add(R.drawable.guide_1);
        guideResIdList.add(R.drawable.guide_2);
        guideResIdList.add(R.drawable.guide_3);
    }

    public void setGuideButtonListener(View.OnClickListener guideButtonListener) {
        this.guideButtonListener = guideButtonListener;
    }

    @Override
    public int getCount() {
        return guideResIdList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        final Context context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pager = inflater.inflate(R.layout.pager_guide, container, false);
        ImageView guideImageView = pager.findViewById(R.id.guideImageView);
        // 最后一页才显示立即体验按钮
        Button experienceButton = pager.findViewById(R.id.experienceButton);
        if (position == guideResIdList.size() - 1) {
            experienceButton.setVisibility(View.VISIBLE);
            if (guideButtonListener != null) {
                experienceButton.setOnClickListener(guideButtonListener);
            }
        } else {
            experienceButton.setVisibility(View.GONE);
        }
        Integer resId = guideResIdList.get(position);
        guideImageView.setImageResource(resId);
        container.addView(pager);
        return pager;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
