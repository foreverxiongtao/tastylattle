package com.desperado.tastylattle.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desperado.tastylattle.R;
import com.desperado.tastylattlelib.base.BaseFragment;
import com.desperado.tastylattlelib.mvp.presenter.BasePresenter;
import com.desperado.tastylattlelib.utils.AbToastUtil;

/**
 * Created by Administrator on 2016/9/19.
 */
public class SecondFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTv_fragment_second;

    @Override
    protected View initCustomerView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        View root = _inflater.inflate(R.layout.fragment_second, null);
        return root;
    }

    @Override
    protected void setEventListener() {
        mTv_fragment_second.setOnClickListener(this);
    }

    @Override
    protected void findViewById(View _root) {
        mTv_fragment_second = (TextView) _root.findViewById(R.id.tv_fragment_second);
    }

    @Override
    protected void setTitleBar() {
        setTitleBarBg(R.color.common_title_blue);
        setTitleLeftViewBg(R.color.common_title_blue);
        setTitleColor(R.color.common_title_white);
        setTitle("fragment2");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }

    @Override
    protected int provideStatusColor() {
        return R.color.common_title_blue;
    }

    @Override
    public void onClick(View v) {
        AbToastUtil.showToast(_mActivity, "第二个fragment");
    }

    public static SecondFragment newInstance() {
        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
