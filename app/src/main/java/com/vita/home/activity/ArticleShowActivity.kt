package com.vita.home.activity

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

import com.vita.home.R
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import com.vita.home.helper.GlideRequestOpts
import com.zzhoujay.markdown.MarkDown
import kotlinx.android.synthetic.main.activity_article_show.*
import kotlinx.android.synthetic.main.content_article_show.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import android.graphics.drawable.ColorDrawable
import java.io.IOException
import android.graphics.drawable.BitmapDrawable
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.os.StrictMode
import com.google.gson.Gson
import com.vita.home.bean.IsLike
import com.vita.home.helper.AccountHelper
import java.net.HttpURLConnection


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

    private fun setupToolbar() {
        setSupportActionBar(tb_article_show)

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupFab()
            = fab_in_article_show.setOnClickListener(
            {
                if (article?.id == null) return@setOnClickListener
                like(article?.id!!)
            })

    private fun initData() {
        val articleId = intent.getIntExtra(Key.KEY_ARTICLE_ID, 0)
        Log.d(TAG, "ArticleId: " + articleId)

        getArticle(articleId)
        isLikeOrNot(articleId)
    }

    private fun getArticle(articleId: Int) =
            Api.get(this).getArticle(articleId, object : Callback<Wrap<Article>> {
                override fun onFailure(call: Call<Wrap<Article>>, t: Throwable) {
                    Log.e(TAG, "onFailure: ", t)
                }

                override fun onResponse(call: Call<Wrap<Article>>, response: Response<Wrap<Article>>) {
                    Log.i(TAG, "onResponse: " + response.body()?.message)
                    if (response.body()?.status == 1) {
                        article = response.body()?.data
                        fillArticle()
                    }
                }
            })

    private fun isLikeOrNot(articleId: Int) {
        if (!AccountHelper.check(this)) {
            return
        }
        Api.get(this).isLikeOrNot(articleId, object : Callback<Wrap<IsLike>> {
            override fun onFailure(call: Call<Wrap<IsLike>>?, t: Throwable?) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<IsLike>>?, response: Response<Wrap<IsLike>>?) {
                Log.i(TAG, "onResponse: " + response?.body()?.message)
                Log.i(TAG, Gson().toJson(response?.body()))
                if (response?.body()?.status == 1) {
                    fillLike(response.body()?.data?.liked!!)
                }
            }
        })
    }

    private fun fillArticle() {
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
        Glide.with(this)
                .load(article?.user?.avatar)
                .apply(GlideRequestOpts.centerCropOpts)
                .into(iv_user_avatar)
        tv_user_name.text = article?.user?.name
        tv_created_at.text = article?.createdAt
//        tv_article_body.text = article?.body
        var spanned = MarkDown.fromMarkdown(article?.body, { source ->
            Log.d(TAG, "source: " + source)
            var drawable: Drawable
            try {
                drawable = drawableFromUrl(source)
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            } catch (e: IOException) {
                Log.e(TAG, "Can't get image", e)
                drawable = ColorDrawable(Color.LTGRAY)
                drawable.setBounds(0, 0,
                        tv_article_body.width - tv_article_body.paddingLeft - tv_article_body.paddingRight, 400)
            }
            return@fromMarkdown drawable
        }, tv_article_body)
        tv_article_body.text = spanned
    }

    private fun fillLike(isLike: Boolean) =
            if (isLike) {
                fab_in_article_show.setImageResource(R.drawable.ic_favorite_accent_24dp)
            } else {
                fab_in_article_show.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }

    private fun like(articleId: Int) =
            Api.get(this).like(articleId, object : Callback<Wrap<IsLike>> {
                override fun onResponse(call: Call<Wrap<IsLike>>?, response: Response<Wrap<IsLike>>?) {
                    Log.i(TAG, "onResponse: " + response?.body()?.message)
                    if (response?.body()?.status == 1) {
                        fillLike(response.body()?.data?.liked!!)
                    }
                }

                override fun onFailure(call: Call<Wrap<IsLike>>?, t: Throwable?) {
                    Log.e(TAG, "onFailure: ", t)
                }
            })

    @Throws(IOException::class)
    private fun drawableFromUrl(url: String): Drawable {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val bmp: Bitmap

        val connection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        val input = connection.inputStream

        bmp = BitmapFactory.decodeStream(input)
        return BitmapDrawable(bmp)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
