package com.vita.home.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Vita on 2016/07/17.
 * <p>
 * 自定义高度的ViewPager，
 * 解决用NestedScrollView嵌套ViewPager时发生的滑动冲突
 */
public class WrapHeightViewPager extends ViewPager {

    private boolean scrollable = true;

    public WrapHeightViewPager(Context context) {
        this(context, null);
    }

    public WrapHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 测量ViewPager高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 0;
        //遍历每个子视图，获取子视图中最大的高度作为ViewPager的高度
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 自定义方法，设置ViewPager是否可以滚动
     *
     * @param enable
     */
    public void setScrollable(boolean enable) {
        scrollable = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (scrollable) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }
}
