package com.vita.home.activity

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.google.gson.Gson

import com.vita.home.R
import com.vita.home.api.Api
import com.vita.home.bean.User
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import com.vita.home.fragment.PersonArticlesFragment
import com.vita.home.fragment.PersonFollowsFragment
import com.vita.home.fragment.PersonLikesFragment
import com.vita.home.fragment.PersonRepliesFragment
import com.vita.home.helper.AccountHelper
import kotlinx.android.synthetic.main.activity_personal_center.*
import kotlinx.android.synthetic.main.content_personal_center.*
import kotlinx.android.synthetic.main.content_personal_center_top.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalCenterActivity : AppCompatActivity(),
        PersonArticlesFragment.OnFragmentInteractionListener,
        PersonRepliesFragment.OnFragmentInteractionListener,
        PersonLikesFragment.OnFragmentInteractionListener,
        PersonFollowsFragment.OnFragmentInteractionListener {

    private val TAG: String = "PersonalCenterActivity"

    private var mUserId: Int? = null
    private var mUser: User? = null

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_center)

        initData()

        setupCollapsingToolbarLayout()
        setupToolbar()
        setupTabAndViewPager()
    }

    private fun initData() {
        mUserId = intent.getIntExtra(Key.KEY_USER_ID, AccountHelper.getUserId(this))
        Log.i(TAG, "userId:: " + mUserId)
        getUser()
    }

    private fun setupCollapsingToolbarLayout() {
        ctl_personal_center.setExpandedTitleColor(0x00000000)
        ctl_personal_center.setCollapsedTitleTextColor(0xffffff)
    }

    private fun setupToolbar() {
        setSupportActionBar(tb_personal_center)

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    private fun initFrags(): List<Fragment> {
        var frags = ArrayList<Fragment>()
        frags.add(PersonArticlesFragment.newInstance(mUserId!!))
        frags.add(PersonRepliesFragment.newInstance(mUserId!!))
        frags.add(PersonLikesFragment.newInstance(mUserId!!))
        frags.add(PersonFollowsFragment.newInstance(mUserId!!))
        return frags
    }

    private fun setupTabAndViewPager() {
        val title = arrayOf("Articles", "Comments", "Likes", "Follows")
        vp_personal_center.setScrollable(true)
        vp_personal_center.adapter = PersonFragsAdapter(
                supportFragmentManager, initFrags(), title)
        tl_personal_center.setupWithViewPager(vp_personal_center)
    }

    private fun getUser() {
        Api.get(this).getUser(mUserId!!, object : Callback<Wrap<User>> {
            override fun onFailure(call: Call<Wrap<User>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Wrap<User>>, response: Response<Wrap<User>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mUser = response.body()?.data
                    fillUser()
                }
            }
        })
    }

    private fun fillUser() {
        if (mUser == null) {
            return
        }
        ctl_personal_center.title = mUser!!.name
        Glide.with(this).load(mUser!!.avatar).into(iv_user_avatar)
        tv_username.text = mUser!!.name
        tv_email.text = mUser!!.email
    }
}

class PersonFragsAdapter(fm: FragmentManager,
                         private var fragList: List<Fragment>,
                         private var titles: Array<String>)
    : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment
            = fragList[position]

    override fun getCount(): Int
            = fragList.size

    override fun getPageTitle(position: Int): CharSequence
            = titles[position]
}
