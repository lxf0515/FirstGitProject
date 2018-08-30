package com.took.firstgit.ui.fragment;


import android.util.Log;

import com.took.firstgit.R;

/**
 * Created by linhonghong on 2015/8/11.
 */
public class ThirdFragment  extends LazyLoadFragment {

    public static ThirdFragment instance() {
        ThirdFragment view = new ThirdFragment();
        return view;
    }

    @Override
    protected int setContentView() {
        return R.layout.third_fragment;
    }

    @Override
    protected void lazyLoad() {
        String message = "ThirdFragment " + (isInit ? "已经初始并已经显示给用户可以加载数据" : "没有初始化不能加载数据");
        Log.d(TAG, message);
        System.out.println("ThirdFragment lazyLoad() " + message);
    }
}