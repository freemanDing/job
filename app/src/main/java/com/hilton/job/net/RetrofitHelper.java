package com.hilton.job.net;


import com.hilton.job.common.Contacts;
import com.hilton.job.net.http.HttpConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 辅助类
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class RetrofitHelper {
    private static String TGA = "RetrofitHelper";
    private volatile static RetrofitHelper mInstance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance(){
        if(mInstance==null){
            synchronized (RetrofitHelper.class){
                if (mInstance == null){
                    mInstance = new RetrofitHelper();
                }
            }
        }
        return mInstance;
    }
    private RetrofitHelper(){
        init();
    }
    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Contacts.DEV_BASE_URL)
                .client(HttpConfig.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }



    public ApiService getServer(){
        return mRetrofit.create(ApiService.class);
    }
    public <T> T getServer(Class<T> service){
        return mRetrofit.create(service);
    }

}
