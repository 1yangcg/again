package com.example.henannews_2.splash;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.example.henannews_2.R;
import com.example.henannews_2.base.BaseActivity;
import com.example.henannews_2.guide.GuideActivity;
import com.example.henannews_2.main.MainActivity;
import com.example.henannews_2.utils.NewsConstants;
import com.example.henannews_2.utils.PreferenceUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View animationLayout = this.findViewById(R.id.animationLayout);
//         startAnimationSet(animationLayout);
        startAnimatorSet(animationLayout);
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    // ---------------封装方法区---------------

    private void startAnimatorSet(View animationLayout) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(animationLayout, "ScaleX", 0, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(animationLayout, "ScaleY", 0, 1);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(animationLayout, "Rotation", 0, 1080);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(animationLayout, "Alpha", 0, 1.0f);
        animatorSet.setDuration(1000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束，启动引导页(GuideActivity)
                boolean startGuideActivity = PreferenceUtils.getBoolean(NewsConstants.START_GUIDE_ACTIVITY, true);
                if (startGuideActivity) {
                    Intent intent = new Intent(context, GuideActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                } else {
                    Intent intent = new Intent(context, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }

            }
        });
        animatorSet.playTogether(scaleX, scaleY, rotation, alpha);
        animatorSet.start();
    }

    /**
     * AnimationSet的使用方法
     */
    private void startAnimationSet(View animationLayout) {
        AnimationSet animationSet = new AnimationSet(false);
        // 创建旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // 创建缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1, 0f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // 创建透明度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1.0f);
        // 加入动画集
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(3000);
        animationSet.setFillAfter(true);
        // 启动动画
        animationLayout.setAnimation(animationSet);
        animationSet.start();
    }

    /**
     *  Animation的使用方法
     */
//    private void startAnimation(ViewGroup animationLayout) {
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setDuration(3000);
//        rotateAnimation.setFillAfter(true);
//        // animationLayout.setAnimation(rotateAnimation);
//        // rotateAnimation.start();
//        // animationLayout.startAnimation(rotateAnimation);
//    }
}
