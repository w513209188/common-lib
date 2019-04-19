package com.wb.baselib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wb.baselib.base.fragment.BaseFragment;
import com.wb.baselib.utils.CommonUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mFragments;
    private List<String> mTitles;
    public ViewPageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }
    public ViewPageAdapter(FragmentManager fm, List<BaseFragment> fragments,List<String> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles=titles;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }
    @Override
    public int getItemPosition(Object object) {
        return android.support.v4.view.PagerAdapter.POSITION_NONE;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(CommonUtil.isEmpty(mFragments)){
            return "";
        }else {
            return mTitles.get(position);
        }
    }
}
