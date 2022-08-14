package com.hilton.job.ui.main;

import android.content.Context;

import com.hilton.job.base.BaseContract;
import com.hilton.job.model.request.JobRequest;
import com.hilton.job.model.response.JobResponse;

/**
 * 主页配置约定
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/16
 */

public interface MainContract {
    interface View extends BaseContract.IView {
        void setJobs(JobResponse response);
    }

    interface Presenter extends BaseContract.IPresenter<View> {
        void getJobs(Context context, JobRequest request);
    }
}
