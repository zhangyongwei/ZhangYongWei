package com.zhangyongwei.zhangyongwei.secondfragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhangyongwei.zhangyongwei.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class TypeFragmentViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> fragments;

    private String[] titles = new String[]{"新帖","热帖","旧帖","好帖","坏帖"};

    public TypeFragmentViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
