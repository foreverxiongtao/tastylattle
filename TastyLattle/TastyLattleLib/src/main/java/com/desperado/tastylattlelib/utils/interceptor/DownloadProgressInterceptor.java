package com.desperado.tastylattlelib.utils.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/4/13 下午11:34
 *
 * 描 述 :下载进度拦截器
 *
 * 修订日期 :
 */
public class DownloadProgressInterceptor implements Interceptor {
    private DownloadProgressListener progressListener;

    public DownloadProgressInterceptor(DownloadProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new DownloadProgressResponseBody(originalResponse.body(), progressListener))
                .build();
    }

    private static class DownloadProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final DownloadProgressListener progressListener;
        private BufferedSource bufferedSource;

        public DownloadProgressResponseBody(ResponseBody responseBody,
                                            DownloadProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;

                    if (null != progressListener) {
                        progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    }
                    return bytesRead;
                }
            };
        }
    }

    public interface DownloadProgressListener {
        /***
         * 下载进度
         *
         * @param bytesRead     已下载的字节数
         * @param contentLength 文件总共的字节数
         * @param done          是否已经下载完成
         */
        void update(long bytesRead, long contentLength, boolean done);
    }
}