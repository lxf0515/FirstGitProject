package com.took.firstgit.ui.auto;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.took.firstgit.R;
import com.took.firstgit.ui.auto.other.CardPagerAdapter;
import com.took.firstgit.ui.main.ScaleViewPagerTransformer;
import com.took.firstgit.utils.DataUtils;

import java.util.Timer;

/**
 * Android无限循环与自动播放ViewPager的简单实现(广告栏)
 * https://blog.csdn.net/u013904672/article/details/53743308
 */
public class AutoViewPagerCardActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private ViewPager viewPager2;
    private IndicatorView mIndicatorView;
    private AutoPagerAdapter autoPagerAdapter;

    /**
     * 轮播图自动轮播消息
     */
    public static final int AUTOBANNER_CODE = 0X1001;
    /**
     * 当前轮播图位置
     */
    private int mBannerPosition;
    /**
     * 自动轮播计时器
     */
    private Timer timer = new Timer();
    /**
     * 自动轮播任务
     */
    private BannerTimerTask mBannerTimerTask;
    /**
     * 用户当前是否点击轮播图
     */
    private boolean mIsUserTouched = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.o_auto_view_pager_card);
        initView();
    }

    private void initView(){
        viewPager = findViewById(R.id.img_viewpager);
        viewPager2= findViewById(R.id.img_viewpager2);
        mIndicatorView = findViewById(R.id.idv_banner);
        autoPagerAdapter = new AutoPagerAdapter(this);
        autoPagerAdapter.setDatas(DataUtils.getBannerList());
        viewPager.setCurrentItem(10000);

        viewPager.setPageTransformer(true, new ScaleViewPagerTransformer(this));
        viewPager.setAdapter(autoPagerAdapter);
        viewPager.setPageMargin(80);

        mIndicatorView.setViewPager(DataUtils.getBannerList().size(), viewPager);

        initTouch();

        startBannerTimer();

        initViewPager2();
    }

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private void initViewPager2(){
        mCardAdapter = new CardPagerAdapter(this);
        mCardAdapter.addCardItem(DataUtils.getBannerList2());
        mCardShadowTransformer = new ShadowTransformer(viewPager2, mCardAdapter);
        mCardShadowTransformer.enableScaling(true);

        viewPager2.setAdapter(mCardAdapter);
        viewPager2.setPageTransformer(true, mCardShadowTransformer);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setPageMargin(100);
    }

    private void initTouch() {
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
                    mIsUserTouched = true;
                } else if (action == MotionEvent.ACTION_UP) {
                    mIsUserTouched = false;
                }
                return false;
            }
        });
    }

    /**
     * 轮播图Handler
     */
    Handler bannerHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // 当用户点击时,不进行轮播
            if (!mIsUserTouched) {
                // 获取当前的位置
                mBannerPosition = viewPager.getCurrentItem();
                // 更换轮播图
                mBannerPosition = (mBannerPosition + 1) % autoPagerAdapter.FAKE_BANNER_SIZE;
                viewPager.setCurrentItem(mBannerPosition);
            }
            return true;
        }
    });

    /**
     * 开始轮播
     */
    public void startBannerTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        if (mBannerTimerTask != null) {
            mBannerTimerTask.cancel();
        }
        mBannerTimerTask = new BannerTimerTask(bannerHandler);
        if (timer != null && mBannerTimerTask != null) {
            // 循环5秒执行
            timer.schedule(mBannerTimerTask, 3000, 3000);
        }
    }

    /**
     * 销毁时,关闭任务,防止异常
     */
    @Override
    public void onDestroy() {
        if (null != mBannerTimerTask) {
            mBannerTimerTask.cancel();
            mBannerTimerTask = null;
        }
        super.onDestroy();
    }

}
