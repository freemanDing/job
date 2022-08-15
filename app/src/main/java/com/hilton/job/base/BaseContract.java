package com.hilton.job.base;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public interface BaseContract {

    interface IPresenter<T extends IView> extends LifecycleObserver {

        void setLifecycleOwner(LifecycleOwner lifecycleOwner);
    }

    interface IView {


        /**
         * 显示请求成功
         *
         * @param message
         */
        void showSuccess(String message);

        /**
         * 失败重试
         *
         * @param message
         */
        void showFailed(String message);

        /**
         * 显示当前网络不可用
         */
        void showNoNet();

        /**
         * 重试
         */
        void onRetry();
    }
}
