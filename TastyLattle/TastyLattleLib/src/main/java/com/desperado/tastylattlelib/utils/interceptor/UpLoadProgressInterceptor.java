package com.desperado.tastylattlelib.utils.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/4/13 下午11:48
 *
 * 描 述 :上传进度拦截器
 *
 * 修订日期 :
 */
public class UpLoadProgressInterceptor implements Interceptor {
    private CountingRequestBody.Listener progressListener;

    public UpLoadProgressInterceptor(CountingRequestBody.Listener progressListener) {
        this.progressListener = progressListener;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        if (originalRequest.body() == null) {
            return chain.proceed(originalRequest);
        }

        Request progressRequest = originalRequest.newBuilder()
                .method(originalRequest.method(),
                        new CountingRequestBody(originalRequest.body(), progressListener))
                .build();

        return chain.proceed(progressRequest);
    }
}
