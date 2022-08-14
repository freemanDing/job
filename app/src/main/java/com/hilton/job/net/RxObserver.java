package com.hilton.job.net;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.CallSuper;

import com.hilton.job.MyApplication;
import com.hilton.job.R;
import com.hilton.job.ui.dialog.LoadingDialog;

import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class RxObserver<T> implements Observer<T> {
    private static final String TAG = "RxObserver";
    protected Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    protected RxObserver(Context context) {
        this(context, MyApplication.getApplication().getString(R.string.loading), true);
    }


    protected RxObserver(Context context, boolean showDialog) {
        this(context, MyApplication.getApplication().getString(R.string.loading), showDialog);

    }

    protected RxObserver(Context context, String msg, boolean showDialog) {
        mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T tBaseResponse) {
        //根据业务来分
        onSuccess(tBaseResponse);
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();

        try {
            if (e instanceof ConnectException || e instanceof NetworkErrorException) {
                onFailure(StatusCode.ERROR_NONETWORK, "网络错误", true);
            } else if (e instanceof TimeoutException) {
                onFailure(StatusCode.ERROR_TIMEOUT, "请求超时", true);
            } else {
                onFailure(StatusCode.ERROR_EXCEPTION, "系统异常", true);
            }
        } catch (Exception e1) {
            onFailure(StatusCode.ERROR_EXCEPTION, "系统异常", true);
        }
    }

    @Override
    public void onComplete() {
        if (showDialog) {
            LoadingDialog.cancelDialogForLoading();
        }
    }

    /**
     * 返回成功
     *
     * @param t 返回体
     */
    protected abstract void onSuccess(T t);

    /**
     * 返回失败  子类覆盖时必须super
     * 可对通用问题进行拦截
     *
     * @param code      错误码
     * @param message   消息
     * @param isShowMsg 是否显示错误消息
     */
    @CallSuper
    protected void onFailure(int code, String message, boolean isShowMsg) {
        if (!TextUtils.isEmpty(message) && isShowMsg) {

        }
        //判断状态码 进行拦截

    }
}

