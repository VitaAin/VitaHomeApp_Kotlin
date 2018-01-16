package com.vita.home.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.bumptech.glide.Glide
import com.vita.home.R
import com.vita.home.adapter.commonrvadapter.RvCommonAdapter
import com.vita.home.adapter.commonrvadapter.ViewHolder
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Articles
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import com.vita.home.helper.AccountHelper
import com.vita.home.utils.NetworkUtils
import com.vita.home.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private val TAG = "ArticlesActivity"
    private var mArticles: Articles? = null
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
        setupArticlesRefreshLayout()
        fillUserInDrawer()
    }

    private fun setupToolbar() {
        setSupportActionBar(tb_main)

        val drawer = findViewById(R.id.drawer_main) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, tb_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupFab()
            = fab_in_main.setOnClickListener {
        startActivity(Intent(this@MainActivity, AddArticleActivity::class.java))
    }

    private fun setupNavView()
            = nav_view_main.setNavigationItemSelectedListener(this@MainActivity)

    private fun setupArticlesRv() {
        rv_all_articles.layoutManager = LinearLayoutManager(this)
        rv_all_articles.hasFixedSize()
        rv_all_articles.itemAnimator = DefaultItemAnimator()
        mArticlesRvAdapter = ArticlesRvAdapter(this, mArticles?.data, R.layout.item_article)
        rv_all_articles.adapter = mArticlesRvAdapter
        mArticlesRvAdapter?.setOnItemClickListener(object : RvCommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, ArticleShowActivity::class.java)
                intent.putExtra(Key.KEY_ARTICLE_ID, mArticles?.data?.get(position)?.id)
                startActivity(intent)
            }
        })
    }

    private fun setupArticlesRefreshLayout() {
        rl_articles.setDelegate(this@MainActivity)
        var refreshViewHolder = BGANormalRefreshViewHolder(this, true)
        rl_articles.setRefreshViewHolder(refreshViewHolder)
        rl_articles.setIsShowLoadingMoreView(true)
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.colorMyAccent)
        refreshViewHolder.setLoadingMoreText("加载中...")
    }

    private fun fillUserInDrawer() {
        val user = AccountHelper.getUser(this)
        Glide.with(this).load(user.avatar).into(nav_view_main.getHeaderView(0).iv_avatar)
        nav_view_main.getHeaderView(0).tv_username.text = user.name
    }

    private fun initData() = getArticles(1, null)

    private fun getArticles(page: Int, tagName: String?) {
        Api.get(this).getArticles(page, tagName, object : Callback<Wrap<Articles>> {
            override fun onFailure(call: Call<Wrap<Articles>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
                endRefresh()
                endLoadMore()
            }

            override fun onResponse(call: Call<Wrap<Articles>>, response: Response<Wrap<Articles>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                endRefresh()
                endLoadMore()
                if (response.body()?.status == 1) {
                    mArticles = response.body()?.data
                    mArticlesRvAdapter?.replaceData(mArticles?.data)
                }
            }
        })
    }

    override fun onBackPressed() =
            if (drawer_main.isDrawerOpen(GravityCompat.START)) {
                drawer_main.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.menu_action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_nav_personal_center -> {
                var intent = Intent(this@MainActivity, PersonalCenterActivity::class.java)
                intent.putExtra(Key.KEY_USER_ID, AccountHelper.getUserId(this))
                startActivity(intent)
            }
            R.id.menu_nav_edit_info -> {
                jumpTo(EditInfoActivity::class.java)
            }
            else -> {
                // Do nothing
            }
        }
//        val drawer = findViewById(R.id.drawer_main) as DrawerLayout
//        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    private fun jumpTo(cls: Class<*>)
            = startActivity(Intent(this@MainActivity, cls))

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {

        if (!NetworkUtils.isNetworkConnected(this)) {
            ToastUtils.showShort(this, "当前网络不可用")
            endLoadMore()
            return false
        }
        if (mArticles?.nextPageUrl == null) {
            ToastUtils.showShort(this, "没有更多数据了")
            endLoadMore()
            return false
        }

        getArticles(mArticles?.currentPage!! + 1, null)
        return true
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            ToastUtils.showShort(this, "当前网络不可用")
            endRefresh()
            return
        }

        getArticles(1, null)
    }

    private fun startRefresh() = rl_articles.beginRefreshing()

    private fun startLoadMore() = rl_articles.beginLoadingMore()

    private fun endRefresh() = rl_articles.endRefreshing()

    private fun endLoadMore() = rl_articles.endLoadingMore()
}

class ArticlesRvAdapter(private val ctx: Context,
                        dataList: List<Article>?, layoutId: Int)
    : RvCommonAdapter<Article>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Article, position: Int) {
        holder.setText(R.id.tv_article_title, item.title!!)
        holder.setText(R.id.tv_article_abstract, item.body!!)
        if (item.coverUrl != null) {
            holder.setVisibility(R.id.iv_article_cover, true)
            Glide.with(ctx).load(item.coverUrl)
                    .into(holder.getView(R.id.iv_article_cover))
        }
    }
}