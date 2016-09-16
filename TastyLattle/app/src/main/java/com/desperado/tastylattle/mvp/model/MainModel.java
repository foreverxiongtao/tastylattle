package com.desperado.tastylattle.mvp.model;

import com.desperado.tastylattle.entity.WeatherInfo;
import com.desperado.tastylattle.mvp.model.base.CustomerModel;

import java.util.Map;

import rx.Observable;

/*
 *
 *
 * 版 权 :@Copyright 北京****科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/13  19:21
 *
 * 描 述 : 主页面底层数据获取
 *
 * 修订日期 :
 */
public class MainModel extends CustomerModel {


    /***
     * 获取天气信息
     *
     * @param params 请求参数
     * @return
     */
    public Observable<WeatherInfo> getWeatherInfo(Map<String, String> params) {
        return getGlobalService().queryweather(params);
    }
}
