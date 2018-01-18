package com.vita.home.activity

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.MenuItem

import com.vita.home.R
import com.vita.home.bean.User
import com.vita.home.fragment.NoticeFollowFragment
import com.vita.home.fragment.NoticeLikeFragment
import com.vita.home.fragment.NoticeReplyFragment
import kotlinx.android.synthetic.main.activity_notifications.*

class NotificationsActivity : AppCompatActivity(),
        NoticeReplyFragment.OnFragmentInteractionListener,
        NoticeFollowFragment.OnFragmentInteractionListener,
        NoticeLikeFragment.OnFragmentInteractionListener {

    private val TAG: String = "NotificationsActivity"

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        setupToolbar()
        setupTabAndViewPager()
    }

    private fun setupToolbar() {
        setSupportActionBar(tb_notifications)

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    private fun initFrags(): List<Fragment> {
        var frags = ArrayList<Fragment>()
        frags.add(NoticeReplyFragment.newInstance())
        frags.add(NoticeFollowFragment.newInstance())
        frags.add(NoticeLikeFragment.newInstance())
        return frags
    }

    private fun setupTabAndViewPager() {
        val titles = arrayOf(getString(R.string.reply), getString(R.string.follow), getString(R.string.thumb_like))
        vp_notifications.adapter = NoticeFragsAdapter(
                supportFragmentManager, initFrags(), titles)
        tl_notifications.setupWithViewPager(vp_notifications)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

class NoticeFragsAdapter(fm: FragmentManager,
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
