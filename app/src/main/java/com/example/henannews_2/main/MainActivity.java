package com.example.henannews_2.main;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.henannews_2.R;
import com.example.henannews_2.base.BaseFragment;
import com.example.henannews_2.base.MvpActivity;
import com.example.henannews_2.main.bean.AvatarBean;
import com.example.henannews_2.main.bean.NewsCategory;
import com.example.henannews_2.main.fragment.GovernmentFragment;
import com.example.henannews_2.main.fragment.HomeFragment;
import com.example.henannews_2.main.fragment.NewsFragment;
import com.example.henannews_2.main.fragment.PictureFragment;
import com.example.henannews_2.main.fragment.ServiceFragment;
import com.example.henannews_2.main.fragment.SettingsFragment;
import com.example.henannews_2.utils.ToastHelper;
import com.example.henannews_2.view.AvatarImageView;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends MvpActivity<MainView, MainPresenter, AvatarBean> implements MainView {

    private long lastTime = 0;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolBarTitleTextView)
    TextView toolBarTitleTextView;


    private AvatarImageView avatarImageView;
    private List<NewsCategory.DataBean.ChildrenBean> children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        View headerView = navigationView.getHeaderView(0);
        avatarImageView = headerView.findViewById(R.id.avatarImageView);
    }

    @Override
    protected void initData() {

        // 请求新闻分类数据
        presenter.getNewsCategory();
        // 点击更换头像
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });
        // 点击切换侧边栏
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
        // 点击切换主页面
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BaseFragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.homeItem:
                        toolbar.setNavigationIcon(null);
                        fragment = new HomeFragment();
                        break;
                    case R.id.newsItem:
                        toolbar.setNavigationIcon(R.drawable.img_menu);
                        fragment = new NewsFragment();
                        break;
                    case R.id.serviceItem:
                        toolbar.setNavigationIcon(null);
                        fragment = new ServiceFragment();
                        break;
                    case R.id.governmentItem:
                        toolbar.setNavigationIcon(R.drawable.img_menu);
                        fragment = new GovernmentFragment();
                        break;
                    case R.id.settingsItem:
                        toolbar.setNavigationIcon(null);
                        fragment = new SettingsFragment();
                        break;
                }
                MainActivity.this
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();
                toolBarTitleTextView.setText(menuItem.getTitle());
                return true;
            }
        });
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && data != null) {
            Uri uri = data.getData();
            ContentResolver resolver = context.getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String path = cursor.getString(cursor.getColumnIndex("_data"));
                presenter.upload(path);
                cursor.close();
            }
        }
    }


    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > 2000) {
            ToastHelper.showShortToast("再按一次退出");
            lastTime = currentTime;
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onSuccess(AvatarBean data) {
        Glide.with(context)
                .load(data.getData().getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if (e != null) {
                            ToastHelper.showShortToast(e.getMessage());
                        }
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        avatarImageView.setImageDrawable(resource);
                        avatarImageView.notifyViewChanged();
                        return true;
                    }
                }).into(avatarImageView);
    }

    public List<NewsCategory.DataBean.ChildrenBean> getChildren() {
        return children;
    }

    @Override
    public void onNewsCategory(NewsCategory category) {
        // 默认选中新闻界面
        bottomNavigationView.setSelectedItemId(R.id.newsItem);
        Menu menu = navigationView.getMenu();
        final List<NewsCategory.DataBean>  data = category.getData();
        children = data.get(0).getChildren();
        for (int i = 0; i < data.size(); i++) {
            String title = data.get(i).getTitle();
            MenuItem item = menu.add(0, i, i, title);
            item.setIcon(R.drawable.menu_arr_normal);
        }
        // 点击侧边栏按钮切换Fragment
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                CharSequence title = menuItem.getTitle();
                BaseFragment fragment = null;
                for (int i = 0; i < data.size(); i++) {
                    if ("新闻".equals(title.toString())) {
                        fragment = new NewsFragment();
                    } else if ("组图".equals(title.toString())) {
                        fragment = new PictureFragment();
                    }
                    MainActivity.this
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                }
                toolBarTitleTextView.setText(title);
                drawerLayout.closeDrawer(Gravity.START);
                return true;
            }
        });

    }

    @Override
    public void onFailure(String message) {
        ToastHelper.showShortToast(message);
    }
}
