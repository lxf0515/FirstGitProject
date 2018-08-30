package com.took.firstgit.ui.fragment;


import android.util.Log;

import com.took.firstgit.R;

/**
 * Created by linhonghong on 2015/8/11.
 */
public class SecondFragment extends LazyLoadFragment {

    public static SecondFragment instance() {
        SecondFragment view = new SecondFragment();
        return view;
    }

    @Override
    protected int setContentView() {
        return R.layout.second_fragment;
    }

    @Override
    protected void lazyLoad() {
        String message = "SecondFragment " + (isInit ? "已经初始并已经显示给用户可以加载数据" : "没有初始化不能加载数据");
        Log.d(TAG, message);
        System.out.println("SecondFragment lazyLoad() " + message);
    }
}