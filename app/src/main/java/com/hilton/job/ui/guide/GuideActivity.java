package com.hilton.job.ui.guide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.hilton.job.R;
import com.hilton.job.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager2 guideVP;
    private List<Integer> mResourceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        guideVP = findViewById(R.id.guideVP);

        mResourceList = new ArrayList<>();
        mResourceList.add(R.drawable.guide);
        mResourceList.add(R.drawable.guide);
        mResourceList.add(R.drawable.guide);
        mResourceList.add(R.drawable.guide);
        GuidePagerAdapter guidePagerAdapter = new GuidePagerAdapter(this, mResourceList);
        guideVP.setAdapter(guidePagerAdapter);
        setGuided();
    }

    /**
     * @name setGuided
     * @return_type void
     */
    private void setGuided() {
        PreferencesUtils.putBoolean(GuideActivity.this, "isFirstIn", false);
    }
}