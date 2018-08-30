package com.took.firstgit.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 使ViewPager不能滑动
 */
public class NoTouchViewPager extends ViewPager{

    private boolean isCanScroll = false;
    private boolean mNoFocus = false; //if true, keep View don't move
    public NoTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NoTouchViewPager(Context context){
        this(context,null);
    }

    /**
     * 设置其是否能滑动换页
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    public void setNoFocus(boolean b){
        mNoFocus = b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mNoFocus) return false;
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);

    }
}
