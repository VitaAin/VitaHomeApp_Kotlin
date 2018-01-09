package com.vita.home.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.vita.home.R
import com.vita.home.adapter.commonrvadapter.RvCommonAdapter
import com.vita.home.adapter.commonrvadapter.ViewHolder
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Articles
import com.vita.home.bean.Wrap
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesActivity : AppCompatActivity() {

    private val TAG = "ArticlesActivity"
    private var mArticleList: List<Article>? = ArrayList()
    private var mArticlesRvAdapter: ArticlesRvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupArticlesRv()
        initData()
    }

    private fun setupArticlesRv() {
        rvArticles.layoutManager = LinearLayoutManager(this)
        rvArticles.hasFixedSize()
        rvArticles.itemAnimator = DefaultItemAnimator()
        mArticlesRvAdapter = ArticlesRvAdapter(this, mArticleList, R.layout.item_article)
        rvArticles.adapter = mArticlesRvAdapter
        mArticlesRvAdapter?.setOnItemClickListener(object : RvCommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(this@ArticlesActivity, ArticleShowActivity::class.java)
                intent.putExtra("ArticleId", mArticleList?.get(position)?.id)
                startActivity(intent)
            }
        })
    }

    private fun initData() {
        Api.get().getArticles(object : Callback<Wrap<Articles>> {
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
}

class ArticlesRvAdapter(ctx: Context, dataList: List<Article>?, layoutId: Int)
    : RvCommonAdapter<Article>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Article, position: Int) {
        holder.setText(R.id.tvArticleTitle, item.title!!)
    }
}