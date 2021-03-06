package com.vita.home.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
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

        // 点击输入框才能弹出软键盘
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)

        setCanceledOnTouchOutside(true)
        setCancelable(true)

        initViews()
    }

    private fun initViews() {
        setTitle(mContext?.getString(R.string.create_tag))
    }

    override fun setTitle(title: CharSequence?) {
        tv_dialog_title.text = title
    }

    override fun show() {
        super.show()
        setButtons()
    }

    fun setButtons() {
        setButtonAction(DialogInterface.BUTTON_NEGATIVE, mContext?.getString(R.string.cancel)!!, this)
        setButtonAction(DialogInterface.BUTTON_POSITIVE, mContext?.getString(R.string.ok)!!, this)
    }

    private fun setButtonAction(whichButton: Int, text: CharSequence, listener: View.OnClickListener)
            : CreateTagDialog {
        when (whichButton) {
            DialogInterface.BUTTON_NEGATIVE -> setBtnStyle(dialog_opt_cancel, text, listener)
            DialogInterface.BUTTON_POSITIVE -> setBtnStyle(dialog_opt_ok, text, listener)
            else -> {
                // Do nothing
            }
        }
        return this
    }

    private fun setBtnStyle(btn: TextView, text: CharSequence, listener: View.OnClickListener) {
        btn.text = text
        btn.setOnClickListener(listener)
    }

    private fun createTag() {
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
            R.id.dialog_opt_cancel -> {
                cancel()
            }
            R.id.dialog_opt_ok -> {
                createTag()
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
