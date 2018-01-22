package com.vita.home.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by Vita on 2016/07/17.
 *
 *
 * 自定义高度的ViewPager，
 * 解决用NestedScrollView嵌套ViewPager时发生的滑动冲突
 */
class WrapHeightViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : ViewPager(context, attrs) {

    private var scrollable = true

    /**
     * 测量ViewPager高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var height = 0
        //遍历每个子视图，获取子视图中最大的高度作为ViewPager的高度
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
            val h = child.measuredHeight
            if (h > height) {
                height = h
            }
        }
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * 自定义方法，设置ViewPager是否可以滚动
     *
     * @param enable
     */
    fun setScrollable(enable: Boolean) {
        scrollable = enable
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (scrollable) {
            super.onInterceptTouchEvent(ev)
        } else false
    }
}
