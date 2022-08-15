package com.hilton.job.ui.search;

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

public class SearchHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    public SearchHistoryAdapter(Context context, List<String> data) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_history_search, parent, false);
        return new JobHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JobHolder) {
            JobHolder jobHolder = (JobHolder) holder;
            jobHolder.historySearchTV.setText(mData.get(position));
            jobHolder.historySearchTV.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mData.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<String> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public class JobHolder extends RecyclerView.ViewHolder {
        public TextView historySearchTV;

        public JobHolder(View itemView) {
            super(itemView);
            historySearchTV = (TextView) itemView.findViewById(R.id.historySearchTV);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String content);
    }
}
