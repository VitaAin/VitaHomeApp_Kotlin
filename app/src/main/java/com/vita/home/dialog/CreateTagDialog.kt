package com.vita.home.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.gson.Gson

import com.vita.home.R
import com.vita.home.api.Api
import com.vita.home.bean.Tag
import com.vita.home.bean.Wrap
import kotlinx.android.synthetic.main.content_create_category.*
import kotlinx.android.synthetic.main.content_dialog_btns.*
import kotlinx.android.synthetic.main.content_dialog_title.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @FileName: com.vita.home.dialog.CreateTagDialog.java
 * @Author: Vita
 * @Date: 2018-01-15 10:53
 * @Usage:
 */
class CreateTagDialog : AlertDialog, View.OnClickListener {

    private val TAG: String = "CreateTagDialog"
    private var mContext: Context? = null

    private var mListener: OnDataCompletedListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, @StyleRes themeResId: Int) : super(context, themeResId) {
        init(context)
    }

    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener) {
        init(context)
    }

    private fun init(context: Context) {
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_create_category)

        setCanceledOnTouchOutside(true)
        setCancelable(true)

        initViews()
    }

    private fun initViews() {
        setTitle("创建标签")
    }

    override fun setTitle(title: CharSequence?) {
        tv_dialog_title.text = title
    }

    override fun show() {
        super.show()
        setButtons()
    }

    fun setButtons() {
        setButtonAction(DialogInterface.BUTTON_NEGATIVE, "取消", this)
        setButtonAction(DialogInterface.BUTTON_POSITIVE, "确定", this)
    }

    private fun setButtonAction(whichButton: Int, text: CharSequence, listener: View.OnClickListener)
            : CreateTagDialog {
        when (whichButton) {
            DialogInterface.BUTTON_NEGATIVE -> setBtnStyle(btn_dialog_cancel, text, listener)
            DialogInterface.BUTTON_POSITIVE -> setBtnStyle(btn_dialog_ok, text, listener)
            else -> {
                // Do nothing
            }
        }
        return this
    }

    private fun setBtnStyle(btn: Button, text: CharSequence, listener: View.OnClickListener) {
        btn.text = text
        btn.setOnClickListener(listener)
    }

    private fun createCategory() {
        var name = et_category_name.text.toString()
        var desc = et_category_desc.text.toString()
        var tag = Tag(name, desc)
        Log.d(TAG, Gson().toJson(tag))
        Api.get(mContext!!).createTag(tag, object : Callback<Wrap<Tag>> {
            override fun onResponse(call: Call<Wrap<Tag>>?, response: Response<Wrap<Tag>>?) {
                Log.i(TAG, "onResponse: " + response?.body()?.message)
                if (response?.body()?.status == 1) {
                    mListener?.onDataCompleted(response.body()?.data!!)
                }
            }

            override fun onFailure(call: Call<Wrap<Tag>>?, t: Throwable?) {
                Log.e(TAG, "onFailure: ", t)
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_dialog_cancel -> {
                cancel()
            }
            R.id.btn_dialog_ok -> {
                createCategory()
                dismiss()
            }
        }
    }

    fun setOnDataCompletedListener(listener: OnDataCompletedListener) {
        mListener = listener
    }

    interface OnDataCompletedListener {
        fun onDataCompleted(tag: Tag)
    }
}
