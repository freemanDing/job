package com.hilton.job.base;

import androidx.lifecycle.LifecycleOwner;

import com.hilton.job.utils.RxLifecycleUtils;
import com.uber.autodispose.AutoDisposeConverter;

public class BasePresenter<T extends BaseContract.IView> implements BaseContract.IPresenter<T> {
    protected T mView;
    private LifecycleOwner mLifecycleOwner;


    public BasePresenter(T view) {
        this.mView = view;
    }

    protected <T> AutoDisposeConverter<T> bindToLife() {
        if (null == mLifecycleOwner)
            throw new NullPointerException("mLifecycleOwner == null");
        return RxLifecycleUtils.bindLifecycle(mLifecycleOwner);
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
    }

}
