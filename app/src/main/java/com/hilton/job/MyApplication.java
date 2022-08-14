package com.hilton.job;

import android.app.Application;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class MyApplication extends Application {
    private static MyApplication mMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
    }

    public static MyApplication getApplication() {
        return mMyApplication;
    }
}
