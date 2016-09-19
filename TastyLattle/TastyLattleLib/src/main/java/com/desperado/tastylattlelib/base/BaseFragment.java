package com.desperado.tastylattlelib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.desperado.tastylattlelib.R;
import com.desperado.tastylattlelib.mvp.presenter.BasePresenter;
import com.jaeger.library.StatusBarUtil;

import me.yokeyword.fragmentation.SupportFragment;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/9/19  14:06
 *
 * 描 述 :fragment基类设计
 *
 * 修订日期 :
 */
public abstract class BaseFragment extends SupportFragment {

    /**
     * 当前的fragment的基类布局
     **/
    private View mFragmentView;


    private BaseActivity mBaseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = initCustomerView(inflater, container, savedInstanceState);
        }
        mBaseActivity = (BaseActivity) _mActivity;
        setTitleBar();
        findViewById(mFragmentView);
        setEventListener();
        initData();
        setStatusBar(provideStatusColor());
        return mFragmentView;
    }

    /**
     * 设置标题栏背景颜色
     *
     * @param color
     */
    public void setTitleBarBg(int color) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleBarBg(color);
        }
    }


    /**
     * 设置标题栏左边view背景
     *
     * @param resid 资源文件id
     */
    public void setTitleLeftViewBg(int resid) {
        if (mBaseActivity != null)
            mBaseActivity.setTitleLeftViewBg(resid);
    }

    /**
     * 设置标题栏左边图标
     *
     * @param resid 资源文件id
     */
    public void setTitleLeftIcon(int resid) {
        if (mBaseActivity != null)
            mBaseActivity.setTitleLeftIcon(resid);
    }

    /**
     * 设置标题栏左边图标
     *
     * @param resid  资源文件id
     * @param width  宽
     * @param height 高
     */
    public void setTitleLeftIcon(int resid, int width, int height) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleLeftIcon(resid, width, height);
        }
    }

    /**
     * 设置标题栏左边text
     *
     * @param text
     */
    public void setTitleLeftText(String text) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleLeftText(text);
        }
    }

    /**
     * 设置标题栏左边text颜色
     *
     * @param color
     */
    public void setTitleLeftTextColor(int color) {
        if (mBaseActivity != null)
            mBaseActivity.setTitleLeftTextColor(color);
    }

    /**
     * 设置标题栏名称
     *
     * @param title 标题名称
     */
    public void setTitle(String title) {
        if (mBaseActivity != null)
            mBaseActivity.setTitle(title);
    }

    /**
     * 设置标题栏名称
     *
     * @param title 标题名称
     */
    public void setTitle(int title) {
        if (mBaseActivity != null)
            mBaseActivity.setTitle(title);
    }

    /**
     * 设置标题栏名称颜色
     *
     * @param color
     */
    public void setTitleColor(int color) {
        if (mBaseActivity != null)
            mBaseActivity.setTitleColor(color);
    }

    /**
     * 设置标题栏右边view背景
     *
     * @param resid 资源文件id
     */
    public void setTitleRightViewBg(int resid) {
        if (mBaseActivity != null)
            mBaseActivity.setTitleRightViewBg(resid);
    }

    /**
     * 设置标题栏右边图标
     *
     * @param resid 资源文件id
     */
    public void setTitleRightIcon(int resid) {
        if (mBaseActivity != null)
            mBaseActivity.setTitleRightIcon(resid);
    }

    /**
     * 设置标题栏右边图标
     *
     * @param resid  资源文件id
     * @param width  宽
     * @param height 高
     */
    public void setTitleRightIcon(int resid, int width, int height) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleRightIcon(resid, width, height);
        }
    }

    /**
     * 设置标题栏右边text
     *
     * @param text
     */
    public void setTitleRightText(String text) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleRightText(text);
        }
    }

    /**
     * 设置标题栏右边text颜色
     *
     * @param color
     */
    public void setTitleRightTextColor(int color) {
        if (mBaseActivity != null)
            mBaseActivity.setTitleRightTextColor(color);
    }

    /**
     * 是否隐藏或者显示标题栏左边view
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleLeftViewShow(boolean boo) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleLeftViewShow(boo);
        }
    }

    /**
     * 是否隐藏或者显示标题栏左边文本
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleLeftTextShow(boolean boo) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleLeftTextShow(boo);
        }
    }

    /**
     * 是否隐藏或者显示标题栏右边view
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleRightViewShow(boolean boo) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleRightViewShow(boo);
        }
    }

    /**
     * 是否隐藏或者显示标题栏右边图标
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleRightIconShow(boolean boo) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleRightIconShow(boo);
        }
    }

    /**
     * 是否隐藏或者显示标题栏右边文本
     *
     * @param boo true 显示  false 隐藏
     */
    public void setTitleRightTextShow(boolean boo) {
        if (mBaseActivity != null) {
            mBaseActivity.setTitleRightTextShow(boo);
        }
    }

    /**
     * 标题栏左边view事件
     *
     * @return
     */
    public LinearLayout getTitleLeftClick() {
        if (mBaseActivity != null) {
            return mBaseActivity.getTitleLeftClick();
        }
        return null;
    }

    /**
     * 标题栏右边view事件
     *
     * @return
     */
    public LinearLayout getTitleRightClick() {
        if (mBaseActivity != null) {
            return mBaseActivity.getTitleRightClick();
        }
        return null;
    }


    /**
     * ACTIVITY_STATE_LOADING 正在加载状态
     * ACTIVITY_STATE_NODATA 没有数据状态
     * ACTIVITY_STATE_NETWORK_ERROR 网络异常状态
     *
     * @param activityState 状态
     * @param text          没有数据显示文案
     */
    public void setActivityLoadingState(int activityState, String text) {
        if (mBaseActivity != null) {
            mBaseActivity.setActivityLoadingState(activityState, text);
        }
    }

    public View getActivityStateView() {
        if (mBaseActivity != null)
            return mBaseActivity.getActivityStateView();
        return null;
    }

    protected void setStatusBar(int _color) {
        try {
            StatusBarUtil.setColor(_mActivity, _mActivity.getResources().getColor(provideStatusColor()), 0);
        } catch (Exception _e) {
            StatusBarUtil.setColor(_mActivity, _mActivity.getResources().getColor(R.color.light_title_grey), 0);
        }
    }

    @Override
    public void onDestroyView() {
        BasePresenter presnter = getCurrentPersenter();
        /**取消当前的业务处理请求*/
        if (presnter != null) {
            presnter.unsubcrib();
        }
        super.onDestroyView();
        ViewGroup parent = (ViewGroup) mFragmentView.getParent();
        if (parent != null) {
            parent.removeView(mFragmentView);
        }
    }

    /**
     * 初始化用户自定义布局
     *
     * @param _inflater
     * @param _container
     * @param _savedInstanceState
     */
    protected abstract View initCustomerView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState);

    /**
     * 给视图设置事件监听
     */
    protected abstract void setEventListener();

    /***
     * 寻找子控件
     *
     * @param _root
     */
    protected abstract void findViewById(View _root);

    /**
     * 设置标题栏
     **/
    protected abstract void setTitleBar();

    /***
     * 设置对应的数据
     */
    protected abstract void initData();

    /***
     * 获取当前的fragment的业务处理类
     *
     * @return
     */
    protected abstract BasePresenter getCurrentPersenter();

    /***
     * 设置系统栏的样式
     *
     * @return
     */
    protected abstract int provideStatusColor();

}
