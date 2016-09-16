package com.desperado.tastylattlelib.mvp.presenter;


import com.desperado.tastylattlelib.mvp.model.BaseModel;
import com.desperado.tastylattlelib.mvp.view.BaseView;
import com.desperado.tastylattlelib.utils.AbLogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/*
 *
 *
 * 版 权 :@Copyright 北京******科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/6/3  11:56
 *
 * 描 述 :业务处理基类
 *
 * 修订日期 :
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> implements PresenterHelper<V, M> {
    private List<Subscriber> mSubscribers = new ArrayList<>();//集合用于添加订阅对象
    private V mView;
    private M mModel;

    public BasePresenter() {
    }

    /**
     * 取消任务订阅
     **/
    public void unsubcrib() {
        try {
            if (mSubscribers != null && !mSubscribers.isEmpty()) {
                for (int i = 0; i < mSubscribers.size(); i++) {
                    Subscriber mSubscriber = mSubscribers.get(i);
                    if (mSubscriber != null && !mSubscriber.isUnsubscribed()) {
                        mSubscriber.unsubscribe();
                        AbLogUtil.i("basePresenter", mSubscriber.getClass().getSimpleName() + "取消订阅成功");
                    }
                }
            }
        } catch (Exception e) {
            AbLogUtil.i("basePresenter", "取消订阅失败....");
        }
    }

    /**
     * 添加当前的订阅对象到集合中去
     **/
    public void addSubscrib(Subscriber _subscriber) {
        mSubscribers.add(_subscriber);
    }

    /***
     * 设置视图
     *
     * @param _view
     */
    @Override
    public void attachView(V _view) {
        this.mView = _view;
    }

    @Override
    public void attachModel(M _model) {
        this.mModel = _model;
    }

    /***
     * 销毁视图
     */
    @Override
    public void detachView() {
        this.mView = null;
    }

    /**
     * 获取视图
     *
     * @return
     */
    public V getView() {
        return mView;
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public M getModel() {
        return mModel;
    }
}
