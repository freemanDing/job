package com.hilton.job.ui.job;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hilton.job.R;
import com.hilton.job.model.response.Job;
import com.hilton.job.ui.detail.JobDetailActivity;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Job> mData;
    private final Context mContext;
    private LayoutInflater mInflater;


    public JobListAdapter(Context context, List<Job> data) {
        this.mData = data;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_job, parent, false);
        return new JobHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JobHolder) {
            JobHolder jobHolder = (JobHolder) holder;
            Job jobItemData = mData.get(position);
            jobHolder.titleTV.setText(jobItemData.getTitle());
            jobHolder.descriptionTV.setText(Html.fromHtml(jobItemData.getDescription()));
            if (jobItemData.getCompany() != null) {
                jobHolder.nameTV.setText(jobItemData.getCompany().getName());

                if (TextUtils.isEmpty(jobItemData.getCompany().getLogoUrl()))
                    jobHolder.iconIV.setVisibility(View.GONE);
                else {
                    jobHolder.iconIV.setVisibility(View.VISIBLE);
                    Glide.with(mContext)
                            .load(jobItemData.getCompany().getLogoUrl())
                            .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                            .into(jobHolder.iconIV);
                }
            }

            jobHolder.rootLayout.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, JobDetailActivity.class);
                intent.putExtra("itemData", jobItemData);
                mContext.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Job> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public class JobHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rootLayout;
        public TextView titleTV, nameTV, descriptionTV;
        public ImageView iconIV;

        public JobHolder(View itemView) {
            super(itemView);
            rootLayout = (RelativeLayout) itemView.findViewById(R.id.rootLayout);
            titleTV = (TextView) itemView.findViewById(R.id.titleTV);
            nameTV = (TextView) itemView.findViewById(R.id.nameTV);
            descriptionTV = (TextView) itemView.findViewById(R.id.descriptionTV);
            iconIV = (ImageView) itemView.findViewById(R.id.iconIV);
        }
    }
}
