package com.took.firstgit.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.took.firstgit.R;
import com.took.firstgit.ui.tab.SimpleCardFragment;
import com.took.firstgit.utils.DataUtils;
import com.took.firstgit.utils.HandlerUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    private Context _context;
    private Banner banner;
    private List<String> images;
    private SmartRefreshLayout _smartRefreshLayout;
    private RecyclerView _recyclerView;
    private AppBarLayout _barLayout;

    public static final int TYPE_REFRESH   = 1;//刷新列表
    public static final int TYPE_LOAD_MORE = 2;//加载更多
    private int _currentPage = 1;

    private LogAdapter adapter;

    private ViewPager        _viewPager;
    private SlidingTabLayout _slidingTabLayout;
    private final String[] mTitles3 = {
            "热门", "iOS", "Android"
            , "前端", "后端", "设计", "工具资源"
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_activity_main_ui);
        _context = this;
        initView();
        initBanner();

        initTabLayout();

        initSmartRefresh();
        initAdapter();

        _barLayout.addOnOffsetChangedListener(this);
    }

    private void initView(){
        _viewPager = findViewById(R.id.img_viewpager);
        _slidingTabLayout = findViewById(R.id.tl_33);

        _barLayout = findViewById(R.id.app_bar_layout);

        banner = findViewById(R.id.main_banner_roll_header_view);
        _smartRefreshLayout = findViewById(R.id.o_main_ui_smart_refresh_layout);
        _recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _recyclerView.setLayoutManager(layoutManager);

    }

    private void initBanner(){
        images = DataUtils.getBannerList();

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置圆形指示器与标题
        banner.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器位置
        banner.setDelayTime(2000);//设置轮播时间
        banner.setImages(images);//设置图片源
        banner.setBannerTitles(DataUtils.getBannerTitleList());//设置标题源
        //banner.offsetLeftAndRight(100);
        //banner.setOffscreenPageLimit(images.size());
        //banner.setPageTransformer(true, new ScalePagerTransformer());
        banner.setImageLoader(new GlideImageLoader(this));
        banner.start();
    }

    private void initTabLayout(){
        for (String title : mTitles3) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        _viewPager.setAdapter(mAdapter);
        _slidingTabLayout.setViewPager(_viewPager);

        _viewPager.setCurrentItem(2);

//        _slidingTabLayout.setTextSelectColor(getResources().getColor(R.color.red));
//        _slidingTabLayout.setTextUnselectColor(getResources().getColor(R.color.colorBlack));
//        _slidingTabLayout.setTextBold(true);
//        _slidingTabLayout.setTextsize(15);
        _slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Log.d(getClass().getName(), "position " + position);
                requestBulkDealList(TYPE_REFRESH);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }



    private void initAdapter(){
        adapter = new LogAdapter(this);
        _recyclerView.setAdapter(adapter);
        adapter.onRefreshData(DataUtils.getDataList(_currentPage));
    }

    /**初始化SmartRefresh**/
    public void initSmartRefresh (){
        _smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        _smartRefreshLayout.setRefreshFooter(new ClassicsFooter(this)
                .setAccentColorId(R.color.colorAccent)//设置字体颜色
                .setPrimaryColorId(R.color.bg_grey)//背景颜色
        );
        _smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                requestBulkDealList(TYPE_REFRESH);
            }
        });
        _smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                _currentPage ++;
                requestBulkDealList(TYPE_LOAD_MORE);
            }
        });
        _smartRefreshLayout.setPrimaryColorsId(R.color.bg_grey, R.color.colorAccent);//全局设置主题颜色
    }

    private void requestBulkDealList(final int type){
        HandlerUtil.runOnUiThreadDelay(new Runnable() {
            @Override
            public void run() {
                if(type == TYPE_REFRESH){
                    _currentPage = 1;
                    _smartRefreshLayout.setNoMoreData(false);
                    adapter.onRefreshData(DataUtils.getDataList(_currentPage));
                    _smartRefreshLayout.finishRefresh();
                }else if(type == TYPE_LOAD_MORE){
                    adapter.onLoadMoreData(DataUtils.getDataList(_currentPage));
                    _smartRefreshLayout.finishLoadMore();
                }
                if(_currentPage == 4){
                    _smartRefreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        },2 * 1000);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(verticalOffset >= 0 && isTop()){
            _smartRefreshLayout.setEnableRefresh(true);
        }else{
            _smartRefreshLayout.setEnableRefresh(false);
        }
    }

    public boolean isTop(){
        return !_recyclerView.canScrollVertically(-1);
    }
    public boolean isBottom(){
        return !_recyclerView.canScrollVertically(1);
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
