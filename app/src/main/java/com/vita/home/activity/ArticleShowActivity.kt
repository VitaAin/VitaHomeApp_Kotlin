package com.vita.home.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View

import com.vita.home.R
import com.vita.home.api.Api
import com.vita.home.bean.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleShowActivity : AppCompatActivity() {

    private val TAG: String = "ArticleShowActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_show)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })

        initData()
    }

    fun initData() {
        var articleId = intent.getIntExtra("ArticleId", 0)
        Log.d(TAG, "ArticleId: " + articleId)

        Api.get().getArticle(articleId, object : Callback<Article> {
            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
            }
        })
    }
}
