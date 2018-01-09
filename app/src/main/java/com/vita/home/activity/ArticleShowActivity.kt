package com.vita.home.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide

import com.vita.home.R
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Wrap
import kotlinx.android.synthetic.main.activity_article_show.*
import kotlinx.android.synthetic.main.content_article_show.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleShowActivity : AppCompatActivity() {

    private val TAG: String = "ArticleShowActivity"
    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_show)
        val toolbar = findViewById(R.id.tbArticleShow) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fabInArticleShow) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })

        initData()
    }

    fun initData() {
        var articleId = intent.getIntExtra("ArticleId", 0)
        Log.d(TAG, "ArticleId: " + articleId)

        Api.get().getArticle(articleId, object : Callback<Wrap<Article>> {
            override fun onFailure(call: Call<Wrap<Article>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Wrap<Article>>, response: Response<Wrap<Article>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    article = response.body()?.data
                    fillArticle()
                }
            }
        })
    }

    fun fillArticle() {
        ctlArticleTitle.title = article?.title
        tvArticleBody.text = article?.body
        Glide.with(this)
                .load(article?.user?.avatar)
                .centerCrop()
                .into(ivUserAvatar)
        tvUserName.text = article?.user?.name
        tvCreatedTime.text = article?.createdAt
    }
}
