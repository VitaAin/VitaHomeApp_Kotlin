package com.vita.home.adapter.commonrvadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import com.vita.home.bean.Articles

import java.util.ArrayList

/**
 * @FileName: com.vita.deepred.adapter.commonrvadapter.RvCommonAdapter.java
 * @Author: Vita
 * @Date: 2016-10-25 10:25
 * @Usage:
 */
open class RvCommonAdapter<T>(private val mContext: Context,
                              private var mDataList: List<T>?,
                              private val mLayoutId: Int)
    : RecyclerView.Adapter<ViewHolder>() {

    var isShowSelect: Boolean = false
        set(showCheck) {
            field = showCheck

            mSelectedPositionArr = if (isShowSelect) {
                SparseBooleanArray(0)
            } else {
                mSelectedPositionArr!!.clear()
                null
            }
            updateData()
        }
    private var mSelectedPositionArr: SparseBooleanArray? = null

    val selectedItemsList: List<T>
        get() {
            val selectedItemsList = ArrayList<T>(0)

            if (mDataList == null || mSelectedPositionArr == null) return selectedItemsList

            mDataList!!.indices
                    .filter { isItemSelected(it) }
                    .mapTo(selectedItemsList) { getItem(it) }
            return selectedItemsList
        }

    private var mOnItemClickListener: OnItemClickListener? = null

    private var mOnItemLongClickListener: OnItemLongClickListener? = null

    fun setItemSelected(position: Int, isSelected: Boolean) {
        if (mSelectedPositionArr == null) return

        mSelectedPositionArr!!.put(position, isSelected)
    }

    fun isItemSelected(position: Int): Boolean =
            mSelectedPositionArr != null && mSelectedPositionArr!!.get(position)

    fun setSelectedAll(isSelectedAll: Boolean) {
        if (mSelectedPositionArr == null) return

        mSelectedPositionArr!!.clear()
        for (position in mDataList!!.indices) {
            mSelectedPositionArr!!.put(position, isSelectedAll)
        }
        updateData()
    }

    fun replaceData(dataList: List<T>?) {
        this.mDataList = dataList
        updateData()
    }

    fun updateData() = notifyDataSetChanged()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder.getInstance(mContext, parent, mLayoutId)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        convert(holder, mDataList!![position], position)

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener { mOnItemClickListener!!.onItemClick(holder.itemView, position) }
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mOnItemLongClickListener!!.onItemLongClick(holder.itemView, position)
                false
            }
        }
    }

    open fun convert(holder: ViewHolder, item: T, position: Int) = Unit

    override fun getItemCount(): Int
            = if (mDataList == null) 0 else mDataList!!.size

    fun getItem(position: Int): T
            = mDataList!![position]

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int)
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener
    }
}
