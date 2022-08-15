package com.hilton.job.ui.job;

import android.content.Context;

import com.hilton.job.base.BaseContract;
import com.hilton.job.model.response.Job;

import java.util.List;

public interface JobListContract {
    interface View extends BaseContract.IView {
        void setJobs(List<Job> response);
    }

    interface Presenter extends BaseContract.IPresenter<View> {
        void getJobList(Context context, String content);
    }
}
