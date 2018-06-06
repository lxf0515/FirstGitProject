package com.took.firstgit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.took.firstgit.fragment.ContactsFragment;
import com.took.firstgit.fragment.FoundFragment;
import com.took.firstgit.fragment.MeFragment;
import com.took.firstgit.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView _navigation;
    private ViewPager _viewPager;
    private MenuItem  _menuItem;
    private boolean   _isScrollViewPager = true;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    _viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    _viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    _viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_dashboard2:
                    _viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){
        _viewPager   = findViewById(R.id.main_view_pager);
        _navigation  = findViewById(R.id.navigation);

        initPointView();
        initViewPager();
    }

    public void initPointView(){
        _navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //避免每一个NavigationView有位移
        BottomNavigationViewHelper.disableShiftMode(_navigation);
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) _navigation.getChildAt(0);

        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;

        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(this).inflate(R.layout.message_red_point_layout, menuView, false);
        //添加到Tab上
        itemView.addView(badge);

        TextView count = badge.findViewById(R.id.tv_msg_count);
        count.setVisibility(View.VISIBLE);
        count.setText(String.valueOf(1));

        //如果没有消息，不需要显示的时候那只需要将它隐藏即可
        //count.setVisibility(View.GONE);
    }


    public void initViewPager(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(ContactsFragment.newInstance(getResources().getString(R.string.title_contacts)));
        fragments.add(FoundFragment.newInstance(getResources().getString(R.string.title_found)));
        fragments.add(MeFragment.newInstance(getResources().getString(R.string.title_me)));
        fragments.add(FoundFragment.newInstance(getResources().getString(R.string.title_more)));

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.setFragments(fragments);
        _viewPager.setAdapter(myPagerAdapter);

        _viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (_menuItem != null) {
                    _menuItem.setChecked(false);
                } else {
                    _navigation.getMenu().getItem(0).setChecked(false);
                }
                _menuItem = _navigation.getMenu().getItem(position);
                _menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
        _viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(_isScrollViewPager){
                    return false;
                }else{
                    return true;
                }
            }
        });
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList;

        public void setFragments(ArrayList<Fragment> fragments) {
            mFragmentList = fragments;
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragmentList.get(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return (mFragmentList != null && mFragmentList.size() >0 ? mFragmentList.size() : 0);
        }
    }
}
