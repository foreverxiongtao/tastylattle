package com.desperado.tastylattle.mvp.presenter;


import com.desperado.tastylattle.entity.WeatherInfo;
import com.desperado.tastylattle.global.NetParams;
import com.desperado.tastylattle.mvp.model.MainModel;
import com.desperado.tastylattle.mvp.view.MainView;
import com.desperado.tastylattlelib.mvp.presenter.BasePresenter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/*
 *
 *
 * 版 权 :@Copyright 北京******科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  10:27
 *
 * 描 述 :主页面业务逻辑处理类
 *
 * 修订日期 :
 */
public class MainPresenter extends BasePresenter<MainView, MainModel> {


    public void updateData(String cityname) {
        Subscriber<WeatherInfo> subscribe = new Subscriber<WeatherInfo>() {
            @Override
            public void onCompleted() {
                getView().hideLoadingView();
            }

            @Override
            public void onError(Throwable e) {
                getView().showError("网络异常");
            }

            @Override
            public void onNext(WeatherInfo _weatherInfo) {
                getView().updateData(_weatherInfo);
            }
        };
        Observable<WeatherInfo> localWeatherInfoObservable = getModel().getWeatherInfo(NetParams.getInstance().queryWeather(cityname));
        localWeatherInfoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                getView().startLoadingView();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(subscribe);
        addSubscrib(subscribe);
    }
}
