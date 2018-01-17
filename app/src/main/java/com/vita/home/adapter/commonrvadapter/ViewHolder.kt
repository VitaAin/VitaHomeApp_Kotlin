package com.vita.home.adapter.commonrvadapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckedTextView
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView

/**
 * @FileName: com.vita.deepred.adapter.commonrvadapter.ViewHolder.java
 * @Author: Vita
 * @Date: 2016-10-25 10:17
 * @Usage:
 */
class ViewHolder(private val mContext: Context, private val convertView: View) : RecyclerView.ViewHolder(convertView) {
    private val mViews: SparseArray<View> = SparseArray()

    fun <T : View> getView(viewId: Int): T? {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = convertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T?
    }


    /**
     * View设置背景颜色（颜色不来自本地资源）
     *
     * @param viewId
     * @param bgColor
     * @return
     */
    fun setBackgroundColor(viewId: Int, bgColor: Int): ViewHolder {
        getView<View>(viewId)?.setBackgroundColor(bgColor)
        return this
    }

    /**
     * View设置背景颜色（颜色来自本地资源）
     *
     * @param viewId
     * @param bgColorResId
     * @return
     */
    fun setBackgroundRes(viewId: Int, bgColorResId: Int): ViewHolder {
        getView<View>(viewId)?.setBackgroundResource(bgColorResId)
        return this
    }

    /**
     * View设置背景Drawable（来自本地Drawable）
     *
     * @param viewId
     * @param bgDrawableId
     * @return
     */
    fun setBackgroundDrawable(viewId: Int, bgDrawableId: Int): ViewHolder {
        getView<View>(viewId)?.setBackgroundDrawable(mContext.resources.getDrawable(bgDrawableId))
        return this
    }

    /**
     * View设置隐藏状态
     *
     * @param viewId
     * @param isVisible 是否显示，true则显示，false则隐藏
     * @return
     */
    fun setVisibility(viewId: Int, isVisible: Boolean): ViewHolder {
        getView<View>(viewId)?.visibility = if (isVisible) View.VISIBLE else View.GONE
        return this
    }

    /**
     * 获取View隐藏状态
     *
     * @param viewId
     * @return One of View.VISIBLE, View.INVISIBLE or View.GONE
     */
    fun getVisibility(viewId: Int): Int = getView<View>(viewId)!!.visibility

    /**
     * TextView设置文本
     *
     * @param viewId
     * @param text
     * @return
     */
    fun setText(viewId: Int, text: String): ViewHolder {
        getView<TextView>(viewId)?.text = text
        return this
    }

    /**
     * TextView设置字体大小
     *
     * @param viewId
     * @param textSize
     * @return
     */
    fun setTextSize(viewId: Int, textSize: Float): ViewHolder {
        getView<TextView>(viewId)?.textSize = textSize
        return this
    }

    /**
     * TextView设置字体大小
     *
     * @param viewId
     * @param unit     单位，如TypedValue.COMPLEX_UNIT_SP
     * @param textSize
     * @return
     */
    fun setTextSize(viewId: Int, unit: Int, textSize: Float): ViewHolder {
        getView<TextView>(viewId)?.setTextSize(unit, textSize)
        return this
    }

    /**
     * TextView设置字体颜色（颜色不来自本地资源）
     *
     * @param viewId
     * @param textColor
     * @return
     */
    fun setTextColor(viewId: Int, textColor: Int): ViewHolder {
        getView<TextView>(viewId)?.setTextColor(textColor)
        return this
    }

    /**
     * TextView设置字体颜色（颜色来自本地资源）
     *
     * @param viewId
     * @param textColorResId
     * @return
     */
    fun setTextColorRes(viewId: Int, textColorResId: Int): ViewHolder {
        getView<TextView>(viewId)?.setTextColor(mContext.resources.getColor(textColorResId))
        return this
    }

    /**
     * ImageView设置本地资源图片
     *
     * @param viewId
     * @param imgResId
     * @return
     */
    fun setImageResource(viewId: Int, imgResId: Int): ViewHolder {
        getView<ImageView>(viewId)?.setImageResource(imgResId)
        return this
    }

    /**
     * ImageView设置Bitmap图片
     *
     * @param viewId
     * @param bitmap
     * @return
     */
    fun setImageBitmap(viewId: Int, bitmap: Bitmap): ViewHolder {
        getView<ImageView>(viewId)?.setImageBitmap(bitmap)
        return this
    }

    /**
     * CompoundButton设置选中状态
     *
     * @param viewId
     * @param isCheck 是否选中，true则选中，false则不选中
     * @return
     */
    fun setChecked(viewId: Int, isCheck: Boolean): ViewHolder {
        val view = getView<View>(viewId)
        if (view is CompoundButton) {
            view.isChecked = isCheck
        } else if (view is CheckedTextView) {
            view.isChecked = isCheck
        }
        return this
    }

    /**
     * ProgressBar设置进度
     *
     * @param viewId
     * @param progress
     * @return
     */
    fun setProgress(viewId: Int, progress: Int): ViewHolder {
        getView<ProgressBar>(viewId)?.progress = progress
        return this
    }

    /**
     * ProgressBar设置进度和最大值
     *
     * @param viewId
     * @param progress
     * @param max
     * @return
     */
    fun setProgress(viewId: Int, progress: Int, max: Int): ViewHolder {
        val pb = getView<ProgressBar>(viewId)
        pb?.max = max
        pb?.progress = progress
        return this
    }

    /**
     * SeekBar设置进度
     *
     * @param viewId
     * @param progress
     * @return
     */
    fun setSeekBarProgress(viewId: Int, progress: Int): ViewHolder {
        getView<SeekBar>(viewId)?.progress = progress
        return this
    }

    /**
     * SeekBar设置进度和最大值
     *
     * @param viewId
     * @param progress
     * @return
     */
    fun setSeekBarProgress(viewId: Int, progress: Int, max: Int): ViewHolder {
        val sb = getView<SeekBar>(viewId)
        sb?.max = max
        sb?.progress = progress
        return this
    }

    /**
     * RatingBar设置进度
     *
     * @param viewId
     * @param rating
     * @return
     */
    fun setRating(viewId: Int, rating: Int): ViewHolder {
        getView<RatingBar>(viewId)?.rating = rating.toFloat()
        return this
    }

    /**
     * RatingBar设置进度和最大值
     *
     * @param viewId
     * @param rating
     * @return
     */
    fun setRating(viewId: Int, rating: Int, max: Int): ViewHolder {
        val rb = getView<RatingBar>(viewId)
        rb?.max = max
        rb?.rating = rating.toFloat()
        return this
    }

    /**
     * View设置标记tag
     *
     * @param viewId
     * @param tag
     * @return
     */
    fun setTag(viewId: Int, tag: Any): ViewHolder {
        getView<View>(viewId)?.tag = tag
        return this
    }

    /**
     * View设置带key的标记tag
     *
     * @param viewId
     * @param tag
     * @return
     */
    fun setTag(viewId: Int, key: Int, tag: Any): ViewHolder {
        getView<View>(viewId)?.setTag(key, tag)
        return this
    }

    /**
     * View设置点击监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnClickListener(viewId: Int, listener: View.OnClickListener): ViewHolder {
        getView<View>(viewId)?.setOnClickListener(listener)
        return this
    }

    /**
     * View设置长按监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnLongClickListener(viewId: Int, listener: View.OnLongClickListener): ViewHolder {
        getView<View>(viewId)?.setOnLongClickListener(listener)
        return this
    }

    /**
     * View设置触摸监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnTouchListener(viewId: Int, listener: View.OnTouchListener): ViewHolder {
        getView<View>(viewId)?.setOnTouchListener(listener)
        return this
    }

    /**
     * AdapterView设置单项点击监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnItemClickListener(viewId: Int, listener: AdapterView.OnItemClickListener): ViewHolder {
        getView<AdapterView<*>>(viewId)?.onItemClickListener = listener
        return this
    }

    /**
     * AdapterView设置单项长按监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnItemLongClickListener(viewId: Int, listener: AdapterView.OnItemLongClickListener): ViewHolder {
        getView<AdapterView<*>>(viewId)?.onItemLongClickListener = listener
        return this
    }

    /**
     * AdapterView设置单项选择监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnItemSelectedListener(viewId: Int, listener: AdapterView.OnItemSelectedListener): ViewHolder {
        getView<AdapterView<*>>(viewId)?.onItemSelectedListener = listener
        return this
    }

    /**
     * CompoundButton设置选中状态改变监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnCheckedChangeListener(viewId: Int, listener: CompoundButton.OnCheckedChangeListener?): ViewHolder {
        getView<CompoundButton>(viewId)?.setOnCheckedChangeListener(listener)
        return this
    }

    /**
     * SeekBar设置改变监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnSeekBarChangeListener(viewId: Int, listener: SeekBar.OnSeekBarChangeListener): ViewHolder {
        getView<SeekBar>(viewId)?.setOnSeekBarChangeListener(listener)
        return this
    }

    /**
     * RatingBar设置改变监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    fun setOnRatingBarChangeListener(viewId: Int, listener: RatingBar.OnRatingBarChangeListener): ViewHolder {
        getView<RatingBar>(viewId)?.onRatingBarChangeListener = listener
        return this
    }

    companion object {

        fun getInstance(context: Context, parent: ViewGroup, layoutId: Int): ViewHolder {
            val itemView = LayoutInflater.from(context).inflate(layoutId, parent, false)
            return ViewHolder(context, itemView)
        }
    }
}
