package com.vita.home.adapter.viewListadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @FileName: com.vita.deepred.adapter.viewlistadapter.ViewListAdapter.java
 * @Author: Vita
 * @Date: 2016-10-20 14:31
 * @Usage:
 */
public abstract class ViewListAdapter<T> extends BaseAdapter {

    protected LayoutInflater mLayoutInflater;
    protected Context mContext;
    protected List<T> mDataList;
    protected int mItemLayoutId;

    public ViewListAdapter(Context context, List<T> dataList, int itemLayoutId) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDataList = dataList;
        this.mItemLayoutId = itemLayoutId;
    }

    public void replaceData(List<T> dataList) {
        this.mDataList = dataList;
        updateData();
    }

    public void updateData() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    protected abstract void convert(ViewHolder holder, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.getInstance(mContext, convertView, parent, mItemLayoutId, position);
    }
}
