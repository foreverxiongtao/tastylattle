package com.desperado.tastylattlelib.mvp.model;

/**
 * Created by Administrator on 2016/9/16.
 */
public interface ModelHelper<T> {
    abstract String provideBaseurl(); //提供服务器的地址

    Class<T> provoideInstance();//提供需要初始化的url字节码文件
}
