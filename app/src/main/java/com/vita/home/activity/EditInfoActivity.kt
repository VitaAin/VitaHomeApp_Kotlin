package com.vita.home.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson

import com.vita.home.R
import com.vita.home.api.Api
import com.vita.home.bean.User
import com.vita.home.bean.Wrap
import com.vita.home.helper.AccountHelper
import kotlinx.android.synthetic.main.activity_edit_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditInfoActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private val TAG: String = "EditInfoActivity"

    private var mUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_info)

        initData()
        initView()
    }

    private fun initData() {
        mUser = AccountHelper.getUser(this)
    }

    private fun initView() {
        Glide.with(this).load(mUser!!.avatar).into(iv_user_avatar)
        et_username.setText(mUser!!.name)
        et_email.setText(mUser!!.email)
        et_real_name.setText(mUser!!.realName)
        var checkedRbId: Int = when {
            TextUtils.equals("男", mUser!!.sex) -> rb_sex_male.id
            TextUtils.equals("女", mUser!!.sex) -> rb_sex_female.id
            else -> -1
        }
        if (checkedRbId != -1) {
            rg_sex.check(checkedRbId)
        }
        et_qq.setText(mUser!!.qq)
        et_city.setText(mUser!!.city)
        et_introduction.setText(mUser!!.introduction)
        rg_sex.setOnCheckedChangeListener(this@EditInfoActivity)
        btn_save_info.setOnClickListener(this@EditInfoActivity)
    }

    private fun updateUser() {
        mUser!!.realName = et_real_name.text.toString()
        mUser!!.qq = et_qq.text.toString()
        mUser!!.city = et_city.text.toString()
        mUser!!.introduction = et_introduction.text.toString()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        var rb = group?.findViewById(checkedId) as RadioButton
        Log.i(TAG, "sex: " + rb.text)
        mUser!!.sex = rb.text.toString()
    }

    override fun onClick(v: View?) = when (v!!.id) {
        R.id.btn_save_info -> {
            updateUser()
            Api.get(this@EditInfoActivity).editUserInfo(mUser!!, object : Callback<Wrap<User>> {
                override fun onFailure(call: Call<Wrap<User>>, t: Throwable) {
                    Log.e(TAG, "onFailure: " + t.toString())
                }

                override fun onResponse(call: Call<Wrap<User>>, response: Response<Wrap<User>>) {
                    Log.i(TAG, "onResponse: " + response.body()?.message)
                    Log.i(TAG, Gson().toJson(response.body()))
                }
            })
        }
        else -> {
            // Do nothing
        }
    }
}
