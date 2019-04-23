package com.example.henannews_2.guide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.henannews_2.R;
import com.example.henannews_2.base.BaseActivity;
import com.example.henannews_2.main.MainActivity;
import com.example.henannews_2.utils.NewsConstants;
import com.example.henannews_2.utils.PreferenceUtils;


public class GuideActivity extends BaseActivity {

    private float distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPager guideViewPager = this.findViewById(R.id.guideViewPager);
        final View redDotView = this.findViewById(R.id.redDotView);
        final ViewGroup dotsLayout = this.findViewById(R.id.dotsLayout);
        GuidePagerAdapter pagerAdapter = new GuidePagerAdapter();
        pagerAdapter.setGuideButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                PreferenceUtils.putBoolean(NewsConstants.START_GUIDE_ACTIVITY, false);
                GuideActivity.this.finish();
            }
        });
        guideViewPager.setAdapter(pagerAdapter);
        gitDotsDistance(dotsLayout);
        // 监听ViewPager滑动事件
        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                redDotView.setTranslationX((position + positionOffset) * distance);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_guide;
    }

    private void gitDotsDistance(final ViewGroup dotsLayout) {
        dotsLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                View childAt0 = dotsLayout.getChildAt(0);
                View childAt1 = dotsLayout.getChildAt(1);
                distance = childAt1.getX() - childAt0.getX();
                System.out.println("小圆点间距：" + distance);
                dotsLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }


}
