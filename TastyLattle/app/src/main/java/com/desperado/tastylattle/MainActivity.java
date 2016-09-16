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
    }

    @Override
    public int provideStatusColor() {
        return R.color.common_item_text_blue_normal;
    }

    @Override
    protected void initTitleBar() {

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
        return null;
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }
}
