package com.desperado.tastylattlelib.mvp.presenter;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/7/15  11:38
 *
 * 描 述 :presenter辅助接口
 *
 * 修订日期 :
 */
public interface PresenterHelper<V, M> {
    void attachView(V _view);    //设置视图view

    void detachView();    //销毁视图view

    void attachModel(M _model); //将数据源附加到业务类上

}
