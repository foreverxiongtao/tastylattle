package com.desperado.tastylattle.global;

import com.desperado.tastylattle.entity.WeatherInfo;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/16.
 */
public interface GlobalService {
    /****
     * 获取天气信息的接口
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("weather/query")
    Observable<WeatherInfo> queryweather(@FieldMap Map<String, String> params);
}
