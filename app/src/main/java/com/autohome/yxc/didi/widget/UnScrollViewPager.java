package com.autohome.yxc.didi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.autohome.yxc.didi.R;
import com.autohome.yxc.didi.util.LogUtil;

/**
 * Description: UnScrollViewPager
 * Creator: yxc
 * date: 2016/12/7 10:58
 */
public class UnScrollViewPager extends ViewPager {

    private boolean isScrollable = false;

    public UnScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.unScrollViewPager);

        isScrollable = ta.getBoolean(R.styleable.unScrollViewPager_scrollable, true);
    }

    public UnScrollViewPager(Context context) {
        super(context);
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isScrollable)
            return super.onTouchEvent(arg0);
        boolean b = super.onTouchEvent(arg0);
        LogUtil.e("onTouchEvent" + b);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isScrollable)
            return super.onInterceptTouchEvent(arg0);
        return false;
    }
}