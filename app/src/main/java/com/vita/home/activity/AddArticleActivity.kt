package com.vita.home.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View

import com.vita.home.R
import com.vita.home.adapter.viewListadapter.ViewHolder
import com.vita.home.adapter.viewListadapter.ViewListAdapter
import com.vita.home.api.Api
import com.vita.home.bean.Category
import com.vita.home.bean.Tag
import com.vita.home.bean.Wrap
import kotlinx.android.synthetic.main.activity_add_article.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddArticleActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "AddArticleActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)

        setupToolbar()
        initData()
    }

    private fun setupToolbar() {
        tb_add_article.title = "Add Article"
        btn_add_article.setOnClickListener(this@AddArticleActivity)
    }

    private fun initData() {
        Api.get(this).getCategories(object : Callback<Wrap<List<Category>>> {
            override fun onFailure(call: Call<Wrap<List<Category>>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Wrap<List<Category>>>, response: Response<Wrap<List<Category>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                fillCategories(response.body()?.data)
            }
        })
        Api.get(this).getTags(object : Callback<Wrap<List<Tag>>> {
            override fun onFailure(call: Call<Wrap<List<Tag>>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Wrap<List<Tag>>>, response: Response<Wrap<List<Tag>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                fillTags(response.body()?.data)
            }
        })
    }

    fun fillCategories(categories: List<Category>?) {
        sp_categories.adapter = CategoriesSpAdapter(this, categories, R.layout.item_category)
    }

    fun fillTags(tags: List<Tag>?) {
        sp_tags.adapter = TagsSpAdapter(this, tags, R.layout.item_category)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_article -> {
                Log.d(TAG, "Article done! ")
            }
            else -> {
                // Do nothing
            }
        }
    }
}

class CategoriesSpAdapter(private val ctx: Context,
                          private var dataList: List<Category>?,
                          private val layoutId: Int)
    : ViewListAdapter<Category>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Category) {
        holder.setText(R.id.tv_title, item.name)
        if (!TextUtils.isEmpty(item.description)) {
            holder.setVisibility(R.id.tv_desc, true)
            holder.setText(R.id.tv_desc, item.description)
        } else {
            holder.setVisibility(R.id.tv_desc, false)
        }
    }
}

class TagsSpAdapter(private val ctx: Context,
                    private var dataList: List<Tag>?,
                    private val layoutId: Int)
    : ViewListAdapter<Tag>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Tag) {
        holder.setText(R.id.tv_title, item.name)
        if (!TextUtils.isEmpty(item.description)) {
            holder.setVisibility(R.id.tv_desc, true)
            holder.setText(R.id.tv_desc, item.description)
        } else {
            holder.setVisibility(R.id.tv_desc, false)
        }
    }
}