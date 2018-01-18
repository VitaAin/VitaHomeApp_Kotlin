package com.vita.home.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.WindowManager
import android.widget.TextView

import com.vita.home.R
import kotlinx.android.synthetic.main.content_dialog_btns.*
import kotlinx.android.synthetic.main.content_dialog_title.*
import kotlinx.android.synthetic.main.content_info_prompt.*

/**
 * @FileName: com.vita.home.dialog.InfoPromptDialog.java
 * @Author: Vita
 * @Date: 2018-01-18 10:53
 * @Usage:
 */
class InfoPromptDialog : AlertDialog, View.OnClickListener {

    private val TAG: String = "InfoPromptDialog"
    private var mContext: Context? = null

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
        setContentView(R.layout.dialog_info_prompt)

        // 点击输入框才能弹出软键盘
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)

        setCanceledOnTouchOutside(true)
        setCancelable(true)

        initViews()
    }

    private fun initViews() {
        setTitle(mContext?.getString(R.string.info_prompt))
    }

    override fun setTitle(title: CharSequence?) {
        tv_dialog_title.text = title
    }

    override fun show() {
        super.show()
        setButtons()
    }

    fun setInfo(info: String) {
        tv_main_info.text = info
    }

    fun setSecondaryInfo(secondaryInfo: String) {
        tv_secondary_info.text = secondaryInfo
    }

    fun setButtons() {
        setButtonAction(DialogInterface.BUTTON_NEGATIVE, mContext?.getString(R.string.cancel)!!, this)
        setButtonAction(DialogInterface.BUTTON_POSITIVE, mContext?.getString(R.string.ok)!!, this)
    }

    fun setButtonAction(whichButton: Int, text: CharSequence, listener: View.OnClickListener)
            : InfoPromptDialog {
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dialog_opt_cancel -> {
                cancel()
            }
            R.id.dialog_opt_ok -> {
                dismiss()
            }
        }
    }
}
