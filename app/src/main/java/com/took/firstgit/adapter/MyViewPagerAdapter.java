package com.took.firstgit.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.took.firstgit.R;
import com.took.firstgit.base.BaseFragment;
import com.took.firstgit.ui.fragment.ContactsFragment;
import com.took.firstgit.ui.fragment.FoundFragment;
import com.took.firstgit.ui.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragmentList = fragments;
    }

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return (mFragmentList != null && mFragmentList.size() > 0 ? mFragmentList.size() : 0);
    }
}
