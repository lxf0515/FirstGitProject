package com.took.firstgit.ui.fragment;

import android.util.Log;

import com.took.firstgit.R;

/**
 * Created by linhonghong on 2015/8/11.
 */
public class FourthFragment extends LazyLoadFragment {

    public static FourthFragment instance() {
        FourthFragment view = new FourthFragment();
        return view;
    }

    @Override
    protected int setContentView() {
        return R.layout.fourth_fragment;
    }

    @Override
    protected void lazyLoad() {
        String message = "FourthFragment " + (isInit ? "已经初始并已经显示给用户可以加载数据" : "没有初始化不能加载数据");
        Log.d(TAG, message);
        System.out.println("FourthFragment lazyLoad() " + message);
    }
}