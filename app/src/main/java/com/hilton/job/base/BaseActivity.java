package com.hilton.job.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hilton.job.utils.ToastUtil;

public abstract class BaseActivity<P extends BaseContract.IPresenter> extends AppCompatActivity implements BaseContract.IView {
    protected Activity mContext;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getActivityLayoutID());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        mPresenter = initPresenter();
        mPresenter.setLifecycleOwner(this);
        getLifecycle().addObserver(mPresenter);
        initView();
        initListener();
        initData();
    }

    /**
     * 设置Activity的布局ID
     *
     * @return
     */
    protected abstract int getActivityLayoutID();

    /**
     * 在子View中初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化Listener
     */
    protected abstract void initListener();

    /**
     * 初始化Data
     */
    protected abstract void initData();


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void showSuccess(String message) {
        ToastUtil.showShort(message);
    }

    @Override
    public void showFailed(String message) {
        ToastUtil.showShort(message);
    }

    @Override
    public void showNoNet() {
        ToastUtil.showShort("无网络");
    }

    @Override
    public void onRetry() {

    }
}
