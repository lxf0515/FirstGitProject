package com.took.firstgit.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.took.firstgit.R;
import com.took.firstgit.ui.fragment.FirstLazyFragment;
import com.took.firstgit.ui.fragment.FourthFragment;
import com.took.firstgit.ui.fragment.SecondFragment;
import com.took.firstgit.ui.fragment.ThirdFragment;
import com.took.firstgit.view.NoTouchViewPager;

public class BottomSlidingTabStripActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    public AdvancedPagerSlidingTabStrip mAPSTS;
    public NoTouchViewPager mNoTouchViewPager;

    private static final int VIEW_FIRST       = 0;
    private static final int VIEW_SECOND	  = 1;
    private static final int VIEW_THIRD       = 2;
    private static final int VIEW_FOURTH      = 3;

    private static final int VIEW_SIZE        = 4;

    private FirstLazyFragment mFirstLazyFragment = null;
    private SecondFragment mSecondFragment = null;
    private ThirdFragment mThirdFragment = null;
    private FourthFragment mFourthFragment = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_sliding_tab_strip);
        initView();
        initViewPager();
    }

    private void initView(){
        mNoTouchViewPager = findViewById(R.id.o_bottom_view_pager);
        mAPSTS = findViewById(R.id.o_bottom_tabs);
    }

    private void initViewPager(){
        mNoTouchViewPager.setOffscreenPageLimit(VIEW_SIZE);
        mNoTouchViewPager.setScanScroll(false);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());

        mNoTouchViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));

        adapter.notifyDataSetChanged();
        mAPSTS.setViewPager(mNoTouchViewPager);
        mAPSTS.setOnPageChangeListener(this);
        mNoTouchViewPager.setCurrentItem(VIEW_FIRST);
        mAPSTS.showDot(VIEW_FIRST,"99+");
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FragmentAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider{

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position >= 0 && position < VIEW_SIZE){
                switch (position){
                    case  VIEW_FIRST:
                        if(null == mFirstLazyFragment)
                            mFirstLazyFragment = FirstLazyFragment.instance();
                        return mFirstLazyFragment;
                    case VIEW_SECOND:
                        if(null == mSecondFragment)
                            mSecondFragment = SecondFragment.instance();
                        return mSecondFragment;
                    case VIEW_THIRD:
                        if(null == mThirdFragment)
                            mThirdFragment = ThirdFragment.instance();
                        return mThirdFragment;
                    case VIEW_FOURTH:
                        if(null == mFourthFragment)
                            mFourthFragment = FourthFragment.instance();
                        return mFourthFragment;
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return VIEW_SIZE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position >= 0 && position < VIEW_SIZE){
                switch (position){
                    case  VIEW_FIRST:
                        return  "first";
                    case  VIEW_SECOND:
                        return  "second";
                    case  VIEW_THIRD:
                        return  "third";
                    case  VIEW_FOURTH:
                        return  "fourth";
                    default:
                        break;
                }
            }
            return null;
        }

        @Override
        public Integer getPageIcon(int index) {
            if(index >= 0 && index < VIEW_SIZE){
                switch (index){
                    case  VIEW_FIRST:
                        return  R.mipmap.home_main_icon_n;
                    case VIEW_SECOND:
                        return  R.mipmap.home_categry_icon_n;
                    case VIEW_THIRD:
                        return  R.mipmap.home_live_icon_n;
                    case VIEW_FOURTH:
                        return  R.mipmap.home_mine_icon_n;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Integer getPageSelectIcon(int index) {
            if(index >= 0 && index < VIEW_SIZE){
                switch (index){
                    case  VIEW_FIRST:
                        return  R.mipmap.home_main_icon_f_n;
                    case VIEW_SECOND:
                        return  R.mipmap.home_categry_icon_f_n;
                    case VIEW_THIRD:
                        return  R.mipmap.home_live_icon_f_n;
                    case VIEW_FOURTH:
                        return  R.mipmap.home_mine_icon_f_n;
                    default:
                        break;
                }
            }
            return 0;
        }

        @Override
        public Rect getPageIconBounds(int position) {
            return null;
        }
    }
}
