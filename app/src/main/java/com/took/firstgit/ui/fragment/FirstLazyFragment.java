package com.took.firstgit.ui.fragment;

import android.util.Log;

import com.took.firstgit.R;


/**
 * Created by linhonghong on 2015/8/11.
 */
public class FirstLazyFragment extends LazyLoadFragment{

    public static FirstLazyFragment instance() {
        FirstLazyFragment view = new FirstLazyFragment();
		return view;
	}

    @Override
    protected int setContentView() {
        return R.layout.first_fragment;
    }

    @Override
    protected void lazyLoad() {
        String message = "FirstLazyFragment " + (isInit ? "已经初始并已经显示给用户可以加载数据" : "没有初始化不能加载数据");
        Log.d(TAG, message);
        System.out.println("FirstFragment lazyLoad() " + message);
    }

    @Override
    protected void stopLoad() {
        Log.d(TAG, "Fragment1" + "已经对用户不可见，可以停止加载数据");
    }

}
