package com.wngbo.www.common_postphoto.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.wngbo.www.common_postphoto.R;
import com.wngbo.www.common_postphoto.adapter.ImageViewpageAdapter;
import com.wngbo.www.common_postphoto.config.ISCameraConfig;
import com.wngbo.www.common_postphoto.config.ISLookConfig;
import com.wngbo.www.common_postphoto.widget.CustomViewPager;

public class ISLookPhotoActivity extends AppCompatActivity {
    private CustomViewPager customViewPager;
    private ISLookConfig config;
    public static void startForResult(Activity activity, ISLookConfig config) {
        Intent intent = new Intent(activity, ISLookPhotoActivity.class);
        intent.putExtra("config", config);
        activity.startActivity(intent);
    }

    public static void startForResult(Fragment fragment, ISLookConfig config) {
        Intent intent = new Intent(fragment.getActivity(), ISLookPhotoActivity.class);
        intent.putExtra("config", config);
        fragment.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_photot_layout);
        config= (ISLookConfig) getIntent().getSerializableExtra("config");
        if(config==null)
            return;
        customViewPager= (CustomViewPager) findViewById(R.id.pager);
        customViewPager.setAdapter(new ImageViewpageAdapter(getSupportFragmentManager(),config.photoPaths));
        customViewPager.setOffscreenPageLimit(config.photoPaths.size());
        customViewPager.setCurrentItem(config.startNum);
        customViewPager.setLocked(config.isLock);
    }
}
