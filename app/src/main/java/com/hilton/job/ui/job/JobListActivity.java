package com.hilton.job.ui.job;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hilton.job.R;
import com.hilton.job.base.BaseActivity;
import com.hilton.job.constant.Constants;
import com.hilton.job.model.response.Job;
import com.hilton.job.ui.search.SearchActivity;
import com.hilton.job.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class JobListActivity extends BaseActivity<JobListContract.Presenter> implements JobListContract.View {

    private LinearLayout searchLL;
    private TextView searchTV;
    private RecyclerView jobListRV;
    private List<Job> mJobList = new ArrayList<>();
    private JobListAdapter mJobAdapter;
    private ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        searchLL = findViewById(R.id.searchLL);
        searchTV = findViewById(R.id.searchTV);
        jobListRV = findViewById(R.id.jobListRV);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        jobListRV.setLayoutManager(layoutManager);
        jobListRV.setHasFixedSize(true);
        mJobAdapter = new JobListAdapter(this, mJobList);
        jobListRV.setAdapter(mJobAdapter);
    }


    @Override
    protected void initListener() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                String searchText = result.getData().getStringExtra(Constants.KEY_SEARCH_CONTENT);
                if (!TextUtils.isEmpty(searchText)) {
                    searchTV.setText(searchText);
                    mPresenter.getJobList(JobListActivity.this, searchText);
                }
            }
        });
        searchLL.setOnClickListener(v -> activityResultLauncher.launch(new Intent(JobListActivity.this, SearchActivity.class)));
    }


    @Override
    protected void initData() {
    }


    @Override
    protected JobListContract.Presenter initPresenter() {
        return new JobListPresenter(this);
    }

    @Override
    public void setJobs(List<Job> mJobList) {
        ToastUtil.showShort(getString(R.string.str_numbers_positions) + mJobList.size());
        mJobAdapter.setData(mJobList);
    }
}