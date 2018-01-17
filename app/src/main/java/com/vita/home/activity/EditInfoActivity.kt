package com.vita.home.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import com.vita.home.utils.ToastUtils
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

        setupToolbar()
        initData()
        initView()
    }

    private fun setupToolbar() {
        setSupportActionBar(tb_edit_info)

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        ab.setDisplayHomeAsUpEnabled(true)
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
        et_phone.setText(mUser!!.phone)
        et_qq.setText(mUser!!.qq)
        et_city.setText(mUser!!.city)
        et_introduction.setText(mUser!!.introduction)

        iv_user_avatar.setOnClickListener(this@EditInfoActivity)
        rg_sex.setOnCheckedChangeListener(this@EditInfoActivity)
    }

    private fun updateUser() {
        mUser!!.realName = et_real_name.text.toString()
        mUser!!.phone = et_phone.text.toString()
        mUser!!.qq = et_qq.text.toString()
        mUser!!.city = et_city.text.toString()
        mUser!!.introduction = et_introduction.text.toString()
    }

    private fun saveUserInfo() {
        updateUser()
        Api.get(this@EditInfoActivity).editUserInfo(mUser!!, object : Callback<Wrap<User>> {
            override fun onFailure(call: Call<Wrap<User>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<User>>, response: Response<Wrap<User>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                Log.i(TAG, Gson().toJson(response.body()))
                if (response.body()?.status == 1) {
                    AccountHelper.updateUser(this@EditInfoActivity, response.body()?.data!!)
                    ToastUtils.showShort(this@EditInfoActivity, "修改成功")
                }
            }
        })
    }

    private val REQUEST_CODE_CHOOSE_USER_AVATAR = 1

    private fun jumpToSystemImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Choose User Avatar"),
                REQUEST_CODE_CHOOSE_USER_AVATAR)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        var rb = group?.findViewById(checkedId) as RadioButton
        Log.i(TAG, "sex: " + rb.text)
        mUser!!.sex = rb.text.toString()
    }

    override fun onClick(v: View?) = when (v!!.id) {
        R.id.iv_user_avatar -> {
            jumpToSystemImage()
        }
        else -> {
            // Do nothing
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_info, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_action_done -> saveUserInfo()
            else -> {
                // Do nothing
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_CHOOSE_USER_AVATAR -> {
                if (resultCode == Activity.RESULT_OK) {
                    val uri: Uri = data?.data!!
                    Log.d(TAG, "onActivityResult: uri: " + uri.path)
                    Glide.with(this).load(uri).into(iv_user_avatar)
                }
            }
        }
    }
}
