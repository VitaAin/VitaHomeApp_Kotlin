package com.vita.home.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson

import com.vita.home.R
import com.vita.home.adapter.viewListadapter.ViewHolder
import com.vita.home.adapter.viewListadapter.ViewListAdapter
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Category
import com.vita.home.bean.Tag
import com.vita.home.bean.Wrap
import com.vita.home.dialog.CreateCategoryDialog
import com.vita.home.dialog.CreateTagDialog
import kotlinx.android.synthetic.main.activity_add_article.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddArticleActivity : AppCompatActivity() {

    private val TAG: String = "AddArticleActivity"

    private var mCategories: MutableList<Category>? = ArrayList()
    private var mTags: MutableList<Tag>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)

        setupToolbar()
        initData()
    }

    private fun setupToolbar() {
        setSupportActionBar(tb_add_article)

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    private fun initData() {
        Api.get(this).getCategories(object : Callback<Wrap<List<Category>>> {
            override fun onFailure(call: Call<Wrap<List<Category>>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Wrap<List<Category>>>, response: Response<Wrap<List<Category>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mCategories?.addAll(response.body()?.data!!)
                    fillCategories(response.body()?.data)
                }
            }
        })
        Api.get(this).getTags(object : Callback<Wrap<List<Tag>>> {
            override fun onFailure(call: Call<Wrap<List<Tag>>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Wrap<List<Tag>>>, response: Response<Wrap<List<Tag>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mTags?.addAll(response.body()?.data!!)
                    fillTags(response.body()?.data)
                }
            }
        })
    }

    fun fillCategories(categories: List<Category>?) {
        sp_categories.adapter = CategoriesSpAdapter(this, mCategories, R.layout.item_category)
    }

    fun fillTags(tags: List<Tag>?) {
        sp_tags.adapter = TagsSpAdapter(this, mTags, R.layout.item_category)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_article, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_action_done -> createArticle()
            R.id.menu_action_create_category -> showCreateCategoryDialog()
            R.id.menu_action_create_tag -> showCreateTagDialog()
            else -> {
                // Do nothing
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createArticle() {
        var newArticle = Article()
        newArticle.title = et_article_title.text.toString()
        newArticle.body = et_article_body.text.toString()
        newArticle.categoryId = (sp_categories.selectedItem as Category).id
        Log.d(TAG, "Article done: " + Gson().toJson(newArticle))
        // TODO create article
    }

    private fun showCreateCategoryDialog() {
        var dialog = CreateCategoryDialog(this)
        dialog.show()
        dialog.setOnDataCompletedListener(object : CreateCategoryDialog.OnDataCompletedListener {
            override fun onDataCompleted(category: Category) {
                mCategories?.add(category)
            }
        })
    }

    private fun showCreateTagDialog() {
        var dialog = CreateTagDialog(this)
        dialog.show()
        dialog.setOnDataCompletedListener(object : CreateTagDialog.OnDataCompletedListener {
            override fun onDataCompleted(tag: Tag) {
                mTags?.add(tag)
            }
        })
    }
}

class CategoriesSpAdapter(ctx: Context, dataList: List<Category>?, layoutId: Int)
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

class TagsSpAdapter(ctx: Context, dataList: List<Tag>?, layoutId: Int)
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