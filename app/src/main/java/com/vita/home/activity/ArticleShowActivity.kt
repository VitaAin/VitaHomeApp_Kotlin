package com.vita.home.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

import com.vita.home.R
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import com.vita.home.helper.GlideRequestOpts
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

        setupToolbar()
        setupFab()

        initData()
    }

    private fun setupToolbar() = setSupportActionBar(tb_article_show)

    private fun setupFab()
            = fab_in_article_show.setOnClickListener({ view ->
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    })

    private fun initData() {
        val articleId = intent.getIntExtra(Key.KEY_ARTICLE_ID, 0)
        Log.d(TAG, "ArticleId: " + articleId)

        Api.get(this).getArticle(articleId, object : Callback<Wrap<Article>> {
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
        ctl_article_show.title = article?.title
        if (article?.coverUrl != null) {
            Glide.with(this)
                    .load(article?.coverUrl)
                    .apply(GlideRequestOpts.centerCropOpts)
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>) {
                            Log.i(TAG, "onResourceReady: in SimpleTarget")
                            ctl_article_show.setBackgroundDrawable(resource)
                        }
                    })
        }
        tv_article_body.text = article?.body
        Glide.with(this)
                .load(article?.user?.avatar)
                .apply(GlideRequestOpts.centerCropOpts)
                .into(iv_user_avatar)
        tv_user_name.text = article?.user?.name
        tv_created_at.text = article?.createdAt
    }
}
