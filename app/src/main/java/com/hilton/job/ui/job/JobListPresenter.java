package com.hilton.job.ui.job;

import android.annotation.SuppressLint;
import android.content.Context;

import com.hilton.job.MyApplication;
import com.hilton.job.R;
import com.hilton.job.base.BasePresenter;
import com.hilton.job.model.request.JobRequest;
import com.hilton.job.model.response.JobResponse;
import com.hilton.job.net.RetrofitHelper;
import com.hilton.job.net.RxObserver;
import com.hilton.job.net.RxSchedulers;
import com.hilton.job.utils.ToastUtil;

public class JobListPresenter extends BasePresenter<JobListContract.View> implements JobListContract.Presenter {

    public JobListPresenter(JobListContract.View view) {
        super(view);
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void getJobList(Context context, String content) {

        JobRequest request = new JobRequest();
        request.setQuery(String.format(MyApplication.getApplication().getResources().getString(R.string.str_graphql_query), content));
        RetrofitHelper.getInstance().getServer().getJobList(request)
                .compose(RxSchedulers.applySchedulers())
                .as(bindToLife())
                .subscribe(new RxObserver<JobResponse>(context) {
                    @Override
                    protected void onSuccess(JobResponse jobResponse) {
                        if (jobResponse == null || jobResponse.getData() == null || jobResponse.getData().getJobs() == null) {
                            ToastUtil.showShort(context.getString(R.string.str_data_error));
                            return;
                        }
                        mView.setJobs(jobResponse.getData().getJobs());
                    }

                    @Override
                    protected void onFailure(int code, String message, boolean isShowMsg) {
                        super.onFailure(code, message, isShowMsg);
                    }
                });
    }
}
