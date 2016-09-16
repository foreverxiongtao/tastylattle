package com.desperado.tastylattlelib.mvp.view;

/**
 * Created by xiongtao on 2016/4/20.
 */
public interface BaseView<T> {
    /**
     * 更新数据
     */
    void updateView(T t);

    /**
     * 隐藏加载框
     */
    void hideLoadingView();

    /**
     * 开始加载框
     */
    void startLoadingView();

    /**
     * 显示错误信息
     */
    void showError(String errMsg);
}
