package com.desperado.tastylattlelib.mvp.model;


import com.desperado.tastylattlelib.utils.conventer.FastJsonConverterFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/*
 *
 *
 * 版 权 :@Copyright 北京优多****有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/15  11:42
 *
 * 描 述 :数据源基类
 *
 * 修订日期 :
 */
public abstract class BaseModel<T> implements ModelHelper<T> {
    private T mGlobalService;

    public BaseModel() {
        configRetrofit();
    }

    public T getGlobalService() {
        return mGlobalService;
    }


    /***
     * 配置retrofit网络配置
     */
    protected void configRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(provideBaseurl()).client(client).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        mGlobalService = retrofit.create(provoideInstance());
    }
}
