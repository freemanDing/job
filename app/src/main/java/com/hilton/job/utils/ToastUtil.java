package com.hilton.job.utils;

import android.content.Context;
import android.widget.Toast;

import com.hilton.job.MyApplication;


/**
 * Toast统一管理类
 */
public class ToastUtil {

    private static Toast toast;

    private static Toast initToast(CharSequence message, int duration) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getApplication(), message, duration);
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return toast;
    }

    public static void showShort(CharSequence message) {
        initToast(message, Toast.LENGTH_LONG).show();
    }


    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        initToast(message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param strResId
     * @param duration
     */
    public static void show(Context context, int strResId, int duration) {
        initToast(context.getResources().getText(strResId), duration).show();
    }
}
