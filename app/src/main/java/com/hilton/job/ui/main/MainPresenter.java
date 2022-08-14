package com.hilton.job.ui.main;

import android.content.Context;

import com.hilton.job.base.BasePresenter;
import com.hilton.job.model.request.JobRequest;
import com.hilton.job.model.response.JobResponse;
import com.hilton.job.net.RetrofitHelper;
import com.hilton.job.net.RxObserver;
import com.hilton.job.net.RxSchedulers;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/16
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void getJobs(Context context, JobRequest request) {
        RetrofitHelper.getInstance().getServer().getJobs(request)
                .compose(RxSchedulers.applySchedulers())
                .as(bindToLife())
                .subscribe(new RxObserver<JobResponse>(context) {
                    @Override
                    protected void onSuccess(JobResponse jobResponse) {
                        mView.setJobs(jobResponse);
                    }

                    @Override
                    protected void onFailure(int code, String message, boolean isShowMsg) {
                        super.onFailure(code, message, isShowMsg);
                    }
                });
    }
}
