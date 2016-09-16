package com.desperado.tastylattle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, null);
        mBtn_fragment_main_commit = (Button) root.findViewById(R.id.btn_fragment_main_commit);
        mBtn_fragment_main_commit.setOnClickListener(this);
        initData();
        return root;
    }

    @Override
    protected void initData() {
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        mMainPresenter.attachModel(new MainModel());
    }

    @Override
    public void RefreshFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

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
        mMainPresenter.updateData("北京");
    }
}
