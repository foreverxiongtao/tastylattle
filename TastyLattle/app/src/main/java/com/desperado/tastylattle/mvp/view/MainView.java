package com.desperado.tastylattle.mvp.view;

import com.desperado.tastylattle.entity.WeatherInfo;
import com.desperado.tastylattlelib.mvp.view.BaseView;

/*
 *
 *
 * 版 权 :@Copyright 北京*****有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/14  10:33
 *
 * 描 述 :主页面视图处理类
 *
 * 修订日期 :
 */
public interface MainView extends BaseView {
    void updateData(WeatherInfo _weatherInfo);
}
