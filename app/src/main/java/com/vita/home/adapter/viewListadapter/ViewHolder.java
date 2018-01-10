package com.vita.home.adapter.viewListadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * ViewListAdapter中的ViewHolder
 *
 * @FileName: com.vita.deepred.adapter.viewlistadapter.ViewHolder.java
 * @Author: Vita
 * @Date: 2016-10-20 13:48
 * @Usage:
 */
public class ViewHolder {

    private Context mContext;
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mContext = context;
        this.mViews = new SparseArray<>();
        this.mPosition = position;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    /**
     * 获取ViewHolder的实例
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder getInstance(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     * 通过控件的id获取对应的控件，如果没有，就加入mViews
     *
     * @param viewId 控件id
     * @return 控件view
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * View设置背景颜色（颜色不来自本地资源）
     *
     * @param viewId
     * @param bgColor
     * @return
     */
    public ViewHolder setBackgroundColor(int viewId, int bgColor) {
        getView(viewId).setBackgroundColor(bgColor);
        return this;
    }

    /**
     * View设置背景颜色（颜色来自本地资源）
     *
     * @param viewId
     * @param bgColorResId
     * @return
     */
    public ViewHolder setBackgroundRes(int viewId, int bgColorResId) {
        getView(viewId).setBackgroundResource(bgColorResId);
        return this;
    }

    /**
     * View设置背景Drawable（来自本地Drawable）
     *
     * @param viewId
     * @param bgDrawableId
     * @return
     */
    public ViewHolder setBackgroundDrawable(int viewId, int bgDrawableId) {
        getView(viewId).setBackgroundDrawable(mContext.getResources().getDrawable(bgDrawableId));
        return this;
    }

    /**
     * View设置隐藏状态
     *
     * @param viewId
     * @param isVisible 是否显示，true则显示，false则隐藏
     * @return
     */
    public ViewHolder setVisibility(int viewId, boolean isVisible) {
        getView(viewId).setVisibility(isVisible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 获取View隐藏状态
     *
     * @param viewId
     * @return One of View.VISIBLE, View。INVISIBLE or View.GONE
     */
    public int getVisibility(int viewId) {
        return getView(viewId).getVisibility();
    }

    /**
     * TextView设置文本
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * TextView设置字体大小
     *
     * @param viewId
     * @param textSize
     * @return
     */
    public ViewHolder setTextSize(int viewId, float textSize) {
        TextView tv = getView(viewId);
        tv.setTextSize(textSize);
        return this;
    }

    /**
     * TextView设置字体大小
     *
     * @param viewId
     * @param unit     单位，如TypedValue.COMPLEX_UNIT_SP
     * @param textSize
     * @return
     */
    public ViewHolder setTextSize(int viewId, int unit, float textSize) {
        TextView tv = getView(viewId);
        tv.setTextSize(unit, textSize);
        return this;
    }

    /**
     * TextView设置字体颜色（颜色不来自本地资源）
     *
     * @param viewId
     * @param textColor
     * @return
     */
    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView tv = getView(viewId);
        tv.setTextColor(textColor);
        return this;
    }

    /**
     * TextView设置字体颜色（颜色来自本地资源）
     *
     * @param viewId
     * @param textColorResId
     * @return
     */
    public ViewHolder setTextColorRes(int viewId, int textColorResId) {
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(textColorResId));
        return this;
    }

    /**
     * ImageView设置本地资源图片
     *
     * @param viewId
     * @param imgResId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int imgResId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(imgResId);
        return this;
    }

    /**
     * ImageView设置Bitmap图片
     *
     * @param viewId
     * @param bitmap
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * CompoundButton设置选中状态
     *
     * @param viewId
     * @param isCheck 是否选中，true则选中，false则不选中
     * @return
     */
    public ViewHolder setChecked(int viewId, boolean isCheck) {
        View view = getView(viewId);
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(isCheck);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(isCheck);
        }
        return this;
    }

    /**
     * ProgressBar设置进度
     *
     * @param viewId
     * @param progress
     * @return
     */
    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar pb = getView(viewId);
        pb.setProgress(progress);
        return this;
    }

    /**
     * ProgressBar设置进度和最大值
     *
     * @param viewId
     * @param progress
     * @param max
     * @return
     */
    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar pb = getView(viewId);
        pb.setMax(max);
        pb.setProgress(progress);
        return this;
    }

    /**
     * SeekBar设置进度
     *
     * @param viewId
     * @param progress
     * @return
     */
    public ViewHolder setSeekBarProgress(int viewId, int progress) {
        SeekBar sb = getView(viewId);
        sb.setProgress(progress);
        return this;
    }

    /**
     * SeekBar设置进度和最大值
     *
     * @param viewId
     * @param progress
     * @return
     */
    public ViewHolder setSeekBarProgress(int viewId, int progress, int max) {
        SeekBar sb = getView(viewId);
        sb.setMax(max);
        sb.setProgress(progress);
        return this;
    }

    /**
     * RatingBar设置进度
     *
     * @param viewId
     * @param rating
     * @return
     */
    public ViewHolder setRating(int viewId, int rating) {
        RatingBar rb = getView(viewId);
        rb.setRating(rating);
        return this;
    }

    /**
     * RatingBar设置进度和最大值
     *
     * @param viewId
     * @param rating
     * @return
     */
    public ViewHolder setRating(int viewId, int rating, int max) {
        RatingBar rb = getView(viewId);
        rb.setMax(max);
        rb.setRating(rating);
        return this;
    }

    /**
     * View设置标记tag
     *
     * @param viewId
     * @param tag
     * @return
     */
    public ViewHolder setTag(int viewId, Object tag) {
        getView(viewId).setTag(tag);
        return this;
    }

    /**
     * View设置带key的标记tag
     *
     * @param viewId
     * @param tag
     * @return
     */
    public ViewHolder setTag(int viewId, int key, Object tag) {
        getView(viewId).setTag(key, tag);
        return this;
    }

    /**
     * View设置点击监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
        return this;
    }

    /**
     * View设置长按监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        getView(viewId).setOnLongClickListener(listener);
        return this;
    }

    /**
     * View设置触摸监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        getView(viewId).setOnTouchListener(listener);
        return this;
    }

    /**
     * AdapterView设置单项点击监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        AdapterView av = getView(viewId);
        av.setOnItemClickListener(listener);
        return this;
    }

    /**
     * AdapterView设置单项长按监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView av = getView(viewId);
        av.setOnItemLongClickListener(listener);
        return this;
    }

    /**
     * AdapterView设置单项选择监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnItemSelectedListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        AdapterView av = getView(viewId);
        av.setOnItemSelectedListener(listener);
        return this;
    }

    /**
     * CompoundButton设置选中状态改变监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton cb = getView(viewId);
        cb.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * SeekBar设置改变监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnSeekBarChangeListener(int viewId, SeekBar.OnSeekBarChangeListener listener) {
        SeekBar sb = getView(viewId);
        sb.setOnSeekBarChangeListener(listener);
        return this;
    }

    /**
     * RatingBar设置改变监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnRatingBarChangeListener(int viewId, RatingBar.OnRatingBarChangeListener listener) {
        RatingBar rb = getView(viewId);
        rb.setOnRatingBarChangeListener(listener);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

    public View getConvertView() {
        return mConvertView;
    }

}
