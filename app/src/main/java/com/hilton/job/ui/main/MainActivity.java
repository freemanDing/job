package com.hilton.job.ui.main;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.hilton.job.R;
import com.hilton.job.base.BaseActivity;
import com.hilton.job.model.response.Job;
import com.hilton.job.model.request.JobRequest;
import com.hilton.job.model.response.JobResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    private EditText searchET;
    private Button searchBtn;
    private RecyclerView jobListRV;
    private List<Job> mJobList = new ArrayList<>();
    private JobAdapter mJobAdapter;

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        searchET = findViewById(R.id.searchET);
        searchBtn = findViewById(R.id.searchBtn);
        jobListRV = findViewById(R.id.jobListRV);
        jobListRV.setLayoutManager(new LinearLayoutManager(this));
        mJobAdapter = new JobAdapter(this, mJobList);
        jobListRV.setAdapter(mJobAdapter);
        setSearchBtnStatus(Boolean.FALSE.booleanValue());
    }


    @Override
    protected void initListener() {
        searchBtn.setOnClickListener(v -> {
            String searchText = searchET.getText().toString().trim();
            searchText = TextUtils.isEmpty(searchText) ? searchET.getHint().toString().trim() : searchText;
            JobRequest jobRequest = new JobRequest();
            jobRequest.setQuery(searchText);
            mPresenter.getJobs(this, jobRequest);
        });
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setSearchBtnStatus(!TextUtils.isEmpty(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void setJobs(JobResponse response) {
        updateUI(response);
    }

    private void updateUI(JobResponse jobResponse) {
        if (jobResponse == null || jobResponse.getData() == null || jobResponse.getData().getJobs() == null) {
            Toast.makeText(this, "接口数据返回为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mJobList = jobResponse.getData().getJobs();
        mJobAdapter.setData(mJobList);
    }

    private void setSearchBtnStatus(boolean booleanValue) {
        searchBtn.setEnabled(booleanValue);
        searchBtn.setSelected(booleanValue);
    }
}