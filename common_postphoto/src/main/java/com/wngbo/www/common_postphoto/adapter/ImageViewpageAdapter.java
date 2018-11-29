package com.wngbo.www.common_postphoto.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wngbo.www.common_postphoto.ui.fragment.ImageDetailFragment;

import java.util.List;

/**
 * Created by desin on 2017/3/29.
 */

public class ImageViewpageAdapter extends FragmentPagerAdapter{
    private List<String> mLists;
    public ImageViewpageAdapter(FragmentManager fm, List<String> mLists) {
        super(fm);
        this.mLists = mLists;
    }
    @Override
    public Fragment getItem(int position) {
        return ImageDetailFragment.newInstance(mLists.get(position));
    }
    @Override
    public int getCount() {
        return mLists==null?0:mLists.size();
    }
}
