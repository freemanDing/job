package com.hilton.job.ui.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.hilton.job.R;
import com.hilton.job.constant.Constants;
import com.hilton.job.utils.PreferencesUtils;
import com.hilton.job.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText searchET;
    private Button searchBtn;
    private RecyclerView historySearchRV;
    private List<String> mSearchHistoryList = new ArrayList<>();
    private SearchHistoryAdapter mSearchHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.str_job_search_title));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        searchET = findViewById(R.id.searchET);
        searchBtn = findViewById(R.id.searchBtn);

        historySearchRV = findViewById(R.id.historySearchRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        historySearchRV.setLayoutManager(linearLayoutManager);

        mSearchHistoryAdapter = new SearchHistoryAdapter(this, mSearchHistoryList);
        historySearchRV.setAdapter(mSearchHistoryAdapter);

        setSearchBtnStatus(Boolean.FALSE.booleanValue());
        initListener();
        initData();

    }


    private void initListener() {
        searchBtn.setOnClickListener(v -> {
            String searchText = searchET.getText().toString().trim();
            if (!TextUtils.isEmpty(searchText)) {
                saveHistorySearchData(searchText);
                Intent intent = new Intent();
                intent.putExtra(Constants.KEY_SEARCH_CONTENT, searchText);
                setResult(Constants.SEARCH_RESULT_CODE, intent);
                this.finish();
            } else {
                ToastUtil.showShort(getString(R.string.str_data_empty));
            }
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

        mSearchHistoryAdapter.setOnItemClickListener(content -> searchET.setText(content));
    }

    private void initData() {
        mSearchHistoryAdapter.setData(getHistorySearchData());
    }


    private void setSearchBtnStatus(boolean booleanValue) {
        searchBtn.setEnabled(booleanValue);
        searchBtn.setSelected(booleanValue);
    }

    private void saveHistorySearchData(String searchText) {
        String content = PreferencesUtils.getString(this, Constants.KEY_HISTORY_SEARCH_LIST_JSON);
        List<String> historySearchList = new ArrayList<>();
        if (!TextUtils.isEmpty(content)) {
            try {
                historySearchList = JSON.parseArray(content, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!historySearchList.contains(searchText)) {
            historySearchList.add(searchText);
            PreferencesUtils.putString(this, Constants.KEY_HISTORY_SEARCH_LIST_JSON, JSON.toJSONString(historySearchList));
        }
    }

    private List<String> getHistorySearchData() {
        String content = PreferencesUtils.getString(this, Constants.KEY_HISTORY_SEARCH_LIST_JSON);
        List<String> historySearchList = new ArrayList<>();
        if (!TextUtils.isEmpty(content)) {
            try {
                historySearchList = JSON.parseArray(content, String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return historySearchList;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}