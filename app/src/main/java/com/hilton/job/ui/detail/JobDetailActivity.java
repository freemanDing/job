package com.hilton.job.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hilton.job.R;
import com.hilton.job.model.response.Job;
import com.hilton.job.utils.ToastUtil;

public class JobDetailActivity extends AppCompatActivity {

    private Job mItemData;
    private TextView companyNameTV;
    private ImageView companyIconIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.str_job_detail_title));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        mItemData = intent.getParcelableExtra("itemData");
        if (mItemData == null) {
            ToastUtil.showShort(getString(R.string.str_data_error));
            return;
        }
        companyIconIV = findViewById(R.id.companyIconIV);
        companyNameTV = findViewById(R.id.companyNameTV);
        ((TextView) findViewById(R.id.titleTV)).setText("Position:" + mItemData.getTitle());

        if (mItemData.getCompany() != null) {

            if (TextUtils.isEmpty(mItemData.getCompany().getName()))
                companyNameTV.setVisibility(View.GONE);
            else
                companyNameTV.setText("Company:" + mItemData.getCompany().getName());

            if (TextUtils.isEmpty(mItemData.getCompany().getLogoUrl()))
                companyIconIV.setVisibility(View.GONE);
            else
                Glide.with(this)
                        .load(mItemData.getCompany().getLogoUrl())
                        .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                        .into(companyIconIV);
        }
        ((TextView) findViewById(R.id.companyDescriptionTV)).setText(Html.fromHtml(mItemData.getDescription()));
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