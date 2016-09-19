package com.desperado.tastylattle.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.desperado.tastylattle.R;
import com.desperado.tastylattle.entity.WeatherInfo;
import com.desperado.tastylattle.mvp.model.MainModel;
import com.desperado.tastylattle.mvp.presenter.MainPresenter;
import com.desperado.tastylattle.mvp.view.MainView;
import com.desperado.tastylattlelib.base.BaseFragment;
import com.desperado.tastylattlelib.mvp.presenter.BasePresenter;

/**
 * Created by Administrator on 2016/9/16.
 */
public class MainFragment extends BaseFragment implements MainView, View.OnClickListener {

    private Button mBtn_fragment_main_commit;
    private MainPresenter mMainPresenter;

    @Override
    protected BasePresenter getCurrentPersenter() {
        return mMainPresenter;
    }


    protected int provideStatusColor() {
        return R.color.common_title_blue;
    }

    protected View initCustomerView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        View root = _inflater.inflate(R.layout.fragment_main, null);
        return root;
    }

    protected void setEventListener() {
        mBtn_fragment_main_commit.setOnClickListener(this);
    }

    protected void findViewById(View _root) {
        mBtn_fragment_main_commit = (Button) _root.findViewById(R.id.btn_fragment_main_commit);
    }

    protected void setTitleBar() {
        setTitleBarBg(R.color.common_title_blue);
        setTitleLeftViewBg(R.color.common_title_blue);
        setTitleColor(R.color.common_title_white);
        setTitle("测试");
    }

    protected void initData() {
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        mMainPresenter.attachModel(new MainModel());
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void updateData(WeatherInfo _weatherInfo) {

    }

    @Override
    public void updateView(Object _o) {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void startLoadingView() {

    }

    @Override
    public void showError(String errMsg) {

    }

    @Override
    public void onClick(View v) {
//        mMainPresenter.updateData("北京");
        start(SecondFragment.newInstance());
    }
}
