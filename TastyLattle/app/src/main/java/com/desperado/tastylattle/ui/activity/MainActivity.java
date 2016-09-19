package com.desperado.tastylattle.ui.activity;

import android.os.Bundle;

import com.desperado.tastylattle.ui.fragment.MainFragment;
import com.desperado.tastylattle.R;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.afl_main_container, MainFragment.newInstance());
        }
//        initTitleBar();
//        initfindViewById();
//        setListener();
//        init();
    }

//    @Override
//    public int provideStatusColor() {
//        return R.color.common_botton_bar_blue;
//    }

//    @Override
//    protected void initTitleBar() {
//        hideTitle();
////        setTitleBarBg(R.color.common_title_blue);
////        setTitleLeftViewBg(R.color.common_title_blue);
////        setTitleColor(R.color.common_title_white);
////        setTitle("测试");
//    }
//
//    @Override
//    protected void initfindViewById() {
//
//    }
//
//    @Override
//    protected void setListener() {
//
//    }
//
//    @Override
//    protected void init() {
//
//    }
//
//    @Override
//    protected Activity getActivity() {
//        return this;
//    }
//
//    @Override
//    protected BasePresenter getCurrentPersenter() {
//        return null;
//    }
}
