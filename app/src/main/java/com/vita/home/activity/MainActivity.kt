package com.vita.home.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.vita.home.R
import com.vita.home.adapter.commonrvadapter.RvCommonAdapter
import com.vita.home.adapter.commonrvadapter.ViewHolder
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Articles
import com.vita.home.bean.User
import com.vita.home.bean.Wrap
import com.vita.home.utils.SPUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "ArticlesActivity"
    private var mArticleList: List<Article>? = ArrayList()
    private var mArticlesRvAdapter: ArticlesRvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setupViews()
        initData()
    }

    private fun setupViews() {
        setupToolbar()
        setupFab()
        setupNavView()
        setupArticlesRv()
        fillUserInDrawer()
    }

    private fun setupToolbar() {
        val toolbar = findViewById(R.id.tb_main) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_main) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupFab() {
        val fab = findViewById(R.id.fab_in_main) as FloatingActionButton
        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddArticleActivity::class.java))
        }
    }

    private fun setupNavView() {
        val navigationView = findViewById(R.id.nav_view_main) as NavigationView
        navigationView.setNavigationItemSelectedListener(this@MainActivity)
    }

    private fun setupArticlesRv() {
        rv_all_articles.layoutManager = LinearLayoutManager(this)
        rv_all_articles.hasFixedSize()
        rv_all_articles.itemAnimator = DefaultItemAnimator()
        mArticlesRvAdapter = ArticlesRvAdapter(this, mArticleList, R.layout.item_article)
        rv_all_articles.adapter = mArticlesRvAdapter
        mArticlesRvAdapter?.setOnItemClickListener(object : RvCommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, ArticleShowActivity::class.java)
                intent.putExtra("ArticleId", mArticleList?.get(position)?.id)
                startActivity(intent)
            }
        })
    }

    private fun fillUserInDrawer() {
        var userJson: String = SPUtils.get(this, "User", "") as String
        val user = Gson().fromJson(userJson, User::class.java)
        Glide.with(this).load(user.avatar).into(nav_view_main.getHeaderView(0).iv_avatar)
        nav_view_main.getHeaderView(0).tv_username.text = user.name
        nav_view_main.getHeaderView(0).tv_email.text = user.email
    }

    private fun initData() {
        Api.get().getArticles(null, object : Callback<Wrap<Articles>> {
            override fun onFailure(call: Call<Wrap<Articles>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Wrap<Articles>>, response: Response<Wrap<Articles>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mArticleList = response.body()?.data?.data
                    mArticlesRvAdapter?.replaceData(mArticleList)
                }
            }
        })
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_main) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
        // Handle the camera action
            R.id.nav_personal_center -> {
                jumpTo(PersonalCenterActivity::class.java)
            }
            R.id.nav_edit_info -> {
            }
//            R.id.nav_my_likes -> {
//            }
//            R.id.nav_my_fans -> {
//            }
//            R.id.nav_my_images -> {
//            }
//            R.id.nav_my_notifications -> {
//            }
            else -> {
                // Do nothing
            }
        }
//        val drawer = findViewById(R.id.drawer_main) as DrawerLayout
//        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    private fun jumpTo(cls: Class<*>) {
        startActivity(Intent(this@MainActivity, cls))
    }
}

class ArticlesRvAdapter(ctx: Context, dataList: List<Article>?, layoutId: Int)
    : RvCommonAdapter<Article>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Article, position: Int) {
        holder.setText(R.id.tv_article_title, item.title!!)
    }
}