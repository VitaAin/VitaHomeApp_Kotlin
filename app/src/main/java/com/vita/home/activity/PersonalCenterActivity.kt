package com.vita.home.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity

import com.vita.home.R
import kotlinx.android.synthetic.main.activity_personal_center.*
import kotlinx.android.synthetic.main.content_personal_center.*

class PersonalCenterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_center)

//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

        initFrags()
        setupTabAndViewPager()
    }

    private fun initFrags(): List<Fragment> {
        var frags = ArrayList<Fragment>()
        return frags
    }

    private fun setupTabAndViewPager() {
        val title = arrayOf("", "", "")
        var adapter = PersonFragsAdapter(supportFragmentManager, initFrags(), title)
        vp_personal_center.adapter = adapter
        tl_personal_center.setupWithViewPager(vp_personal_center)
    }
}

class PersonFragsAdapter(fm: FragmentManager, private var fragList: List<Fragment>, private var titles: Array<String>)
    : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment
            = fragList[position]

    override fun getCount(): Int
            = fragList.size
}
