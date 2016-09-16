package com.desperado.tastylattle;

import android.app.Activity;
import android.os.Bundle;

import com.desperado.tastylattlelib.base.BaseActivity;
import com.desperado.tastylattlelib.mvp.presenter.BasePresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.afl_main_container, MainFragment.newInstance());
        }
        initTitleBar();
        initfindViewById();
        setListener();
        init();
    }

    @Override
    public int provideStatusColor() {
        return R.color.common_item_text_blue_normal;
    }

    @Override
    protected void initTitleBar() {
        setTitleBarBg(R.color.common_title_blue);
        setTitleLeftViewBg(R.color.common_title_blue);
        setTitleColor(R.color.common_title_white);
        setTitle("测试");
    }

    @Override
    protected void initfindViewById() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }
}
