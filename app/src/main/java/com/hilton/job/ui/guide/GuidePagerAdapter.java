package com.hilton.job.ui.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hilton.job.R;
import com.hilton.job.ui.job.JobListActivity;

import java.util.List;

public class GuidePagerAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<Integer> mData;

    public GuidePagerAdapter(Context context, List<Integer> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_guide, parent, false);
        return new GuideHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof GuideHolder) {
            GuideHolder guideHolder = (GuideHolder) holder;
            Integer resourceId = mData.get(position);
            Glide.with(mContext).load(resourceId).into(guideHolder.guideIV);
            if (position == mData.size() - 1) {
                guideHolder.startBtn.setVisibility(View.VISIBLE);
                guideHolder.startBtn.setOnClickListener(v ->
                {
                    mContext.startActivity(new Intent(mContext, JobListActivity.class));
                    ((Activity) mContext).finish();
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class GuideHolder extends RecyclerView.ViewHolder {
        ImageView guideIV;
        Button startBtn;

        public GuideHolder(View itemView) {
            super(itemView);
            guideIV = (ImageView) itemView.findViewById(R.id.guideIV);
            startBtn = (Button) itemView.findViewById(R.id.startBtn);
        }
    }


}


