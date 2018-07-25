package com.took.firstgit.ui.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.took.firstgit.R;

import java.util.ArrayList;

public class SlidingTabStripActivity extends AppCompatActivity{

    private String[] mTitles = {"首页", "消息"};
    private String[] mTitles_2 = {"首页", "消息", "联系人"};

    private final String[] mTitles3 = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    SegmentTabLayout segmentTabLayout;
    SegmentTabLayout segmentTabLayout4;
    SlidingTabLayout slidingTabLayout;

    ViewPager vp;
    private MyPagerAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_tab_strip);

        segmentTabLayout = findViewById(R.id.tl_1);
        segmentTabLayout.setTabData(mTitles);

        segmentTabLayout4 = findViewById(R.id.tl_4);
        segmentTabLayout4.setTabData(mTitles_2);

        slidingTabLayout = findViewById(R.id.tl_3);

        for (String title : mTitles3) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }

        vp = findViewById(R.id.vp);

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(vp);

        vp.setCurrentItem(2);
        setListener();
    }

    private void setListener(){
        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Log.d(getClass().getName(), "position " + position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        segmentTabLayout4.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Log.d(getClass().getName(), "position " + position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles3[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
