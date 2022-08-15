package com.hilton.job.net.http;

import com.hilton.job.net.interceptor.HeadRequestInterceptor;
import com.hilton.job.net.interceptor.VLogInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpConfig {
    public static String TGA = "HttpConfig";
    public static long CONNECT_TIMEOUT = 60L;
    public static long READ_TIMEOUT = 30L;
    public static long WRITE_TIMEOUT = 30L;

    /**
     * 获取OkHttpClient实例
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HeadRequestInterceptor())
                .addInterceptor(new VLogInterceptor())
                .build();
        return okHttpClient;
    }
}
