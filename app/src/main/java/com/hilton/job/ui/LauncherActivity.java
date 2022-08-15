package com.hilton.job.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;

import com.hilton.job.R;
import com.hilton.job.constant.Constants;
import com.hilton.job.ui.guide.GuideActivity;
import com.hilton.job.ui.job.JobListActivity;
import com.hilton.job.utils.PreferencesUtils;

public class LauncherActivity extends AppCompatActivity {

    private boolean isFirstIn = false;
    private static final int GO_HOME = 0x11;
    private static final int GO_GUIDE = 0x22;
    private static final long SPLASH_DELAY_MILLIS = 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);
        isFirstIn = PreferencesUtils.getBoolean(LauncherActivity.this, Constants.KEY_GUIDE_FIRST_IN_STATUS, true);
        if (!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    startActivity(new Intent(LauncherActivity.this, JobListActivity.class));
                    break;
                case GO_GUIDE:
                    startActivity(new Intent(LauncherActivity.this, GuideActivity.class));
                    break;
                default:
            }
            LauncherActivity.this.finish();
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}