package com.hilton.job.ui.job;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    public static int SEARCH_REQUEST_CODE = 0x11;

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
        mJobAdapter = new JobListAdapter(this, mJobList);
        jobListRV.setAdapter(mJobAdapter);
    }


    @Override
    protected void initListener() {
        searchLL.setOnClickListener(v ->
                startActivityForResult(new Intent(JobListActivity.this, SearchActivity.class), SEARCH_REQUEST_CODE));
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == Constants.SEARCH_RESULT_CODE) {
            String searchText = data.getStringExtra(Constants.KEY_SEARCH_CONTENT);
            if (!TextUtils.isEmpty(searchText)) {
                searchTV.setText(searchText);
                mPresenter.getJobList(this, searchText);
            }
        }
    }

    @Override
    protected JobListContract.Presenter initPresenter() {
        return new JobListPresenter(this);
    }

    @SuppressLint({"StringFormatInvalid", "StringFormatMatches"})
    @Override
    public void setJobs(List<Job> mJobList) {
        ToastUtil.showShort(String.format(getString(R.string.str_numbers_positions), mJobList.size()));
        mJobAdapter.setData(mJobList);
    }
}