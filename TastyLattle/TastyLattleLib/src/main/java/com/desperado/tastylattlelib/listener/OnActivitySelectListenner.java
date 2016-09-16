package com.desperado.tastylattlelib.listener;

/*
 *
 *
 * 版 权 :@Copyright 北京******科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/17  16:02
 *
 * 描 述 :actvity和fragment通信接口
 *
 * 修订日期 :
 */
public interface OnActivitySelectListenner {
    void showLoginDialog(String message);

    void showErrorMessageg(String error);

    void showSuccessMessage(String msg);

    void showInfoMessage(String msg);

    void dismissloading();
}
