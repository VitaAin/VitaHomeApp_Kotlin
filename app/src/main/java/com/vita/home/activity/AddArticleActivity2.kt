package com.vita.home.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import com.vita.home.R
import com.vita.home.adapter.commonrvadapter.RvCommonAdapter
import com.vita.home.adapter.commonrvadapter.ViewHolder
import com.vita.home.api.Api
import com.vita.home.bean.Article
import com.vita.home.bean.Category
import com.vita.home.bean.Tag
import com.vita.home.bean.Wrap
import com.vita.home.dialog.CreateCategoryDialog
import com.vita.home.dialog.CreateTagDialog
import com.vita.home.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_add_article2.*
import kotlinx.android.synthetic.main.app_bar_add_article2.*
import kotlinx.android.synthetic.main.content_add_article2.*
import kotlinx.android.synthetic.main.content_add_article_drawer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddArticleActivity2 : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "AddArticleActivity2"
    private var mCategories: MutableList<Category> = ArrayList()
    private var mTags: MutableList<Tag> = ArrayList()
    private var mCategoriesRvAdapter: CategoriesRvAdapter? = null
    private var mTagsRvAdapter: TagsRvAdapter? = null
    private var mSelectedCategory: Category? = null
    private var mSelectedTags: MutableList<Tag> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article2)

        setupViews()
        initData()
    }

    private fun setupViews() {
        setupToolbar()
        setupFab()
        setupDrawer()
    }

    private fun setupToolbar() {
        setSupportActionBar(tb_add_article2)

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupFab() =
            fab_in_add_article2.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }

    private fun setupDrawer() {
        setupRvsInDrawer()

        iv_add_category.setOnClickListener(this@AddArticleActivity2)
        iv_add_tag.setOnClickListener(this@AddArticleActivity2)
    }

    private fun setupRvsInDrawer() {
        var categoriesLM = FlexboxLayoutManager(this)
        categoriesLM.flexDirection = FlexDirection.ROW
        categoriesLM.flexWrap = FlexWrap.WRAP
        categoriesLM.justifyContent = JustifyContent.FLEX_START
        rv_categories.layoutManager = categoriesLM
        var tagsLM = FlexboxLayoutManager(this)
        tagsLM.flexDirection = FlexDirection.ROW
        tagsLM.flexWrap = FlexWrap.WRAP
        tagsLM.justifyContent = JustifyContent.FLEX_START
        rv_tags.layoutManager = tagsLM

        mCategoriesRvAdapter = CategoriesRvAdapter(this, mCategories, R.layout.item_option_tag)
        rv_categories.adapter = mCategoriesRvAdapter
        mTagsRvAdapter = TagsRvAdapter(this, mTags, R.layout.item_option_tag)
        rv_tags.adapter = mTagsRvAdapter
    }

    private fun initData() {
        Api.get(this).getCategories(object : Callback<Wrap<List<Category>>> {
            override fun onFailure(call: Call<Wrap<List<Category>>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<List<Category>>>, response: Response<Wrap<List<Category>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                Log.i(TAG, Gson().toJson(response.body()?.data))
                if (response.body()?.status == 1) {
                    mCategories.addAll(response.body()?.data!!)
                }
            }
        })
        Api.get(this).getTags(object : Callback<Wrap<List<Tag>>> {
            override fun onFailure(call: Call<Wrap<List<Tag>>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<List<Tag>>>, response: Response<Wrap<List<Tag>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                Log.i(TAG, Gson().toJson(response.body()?.data))
                if (response.body()?.status == 1) {
                    mTags.addAll(response.body()?.data!!)
                }
            }
        })
    }

    private fun createArticle() {
        var newArticle = Article()
        newArticle.title = et_article_title.text.toString()
        newArticle.body = et_article_body.text.toString()
        if (mSelectedCategory == null) {
            ToastUtils.showShort(this, "请选择分类")
            return
        }
        newArticle.categoryId = mSelectedCategory?.id!!
        Log.d(TAG, "Article done: " + Gson().toJson(newArticle))
        // TODO create article
    }

    private fun showCreateCategoryDialog() {
        var dialog = CreateCategoryDialog(this)
        dialog.show()
        dialog.setOnDataCompletedListener(object : CreateCategoryDialog.OnDataCompletedListener {
            override fun onDataCompleted(category: Category) {
                mCategories.add(category)
                mCategoriesRvAdapter?.updateData()
            }
        })
    }

    private fun showCreateTagDialog() {
        var dialog = CreateTagDialog(this)
        dialog.show()
        dialog.setOnDataCompletedListener(object : CreateTagDialog.OnDataCompletedListener {
            override fun onDataCompleted(tag: Tag) {
                mTags.add(tag)
                mTagsRvAdapter?.updateData()
            }
        })
    }

    private fun toggleDrawer() =
            if (drawer_add_article2.isDrawerOpen(GravityCompat.END)) {
                drawer_add_article2.closeDrawer(GravityCompat.END)
            } else {
                drawer_add_article2.openDrawer(GravityCompat.END)
            }

    override fun onClick(v: View?) =
            when (v?.id) {
                R.id.iv_add_category -> showCreateCategoryDialog()
                R.id.iv_add_tag -> showCreateTagDialog()
                else -> {
                    // Do nothing
                }
            }

    override fun onBackPressed() =
            if (drawer_add_article2.isDrawerOpen(GravityCompat.END)) {
                drawer_add_article2.closeDrawer(GravityCompat.END)
            } else {
                super.onBackPressed()
            }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_article2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.menu_action_done -> createArticle()
            R.id.menu_action_options -> toggleDrawer()
            else -> {
                // Do nothing
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

private class CategoriesRvAdapter(ctx: Context, private val dataList: List<Category>?, layoutId: Int)
    : RvCommonAdapter<Category>(ctx, dataList, layoutId) {
    var selectedCategory: Category? = null

    override fun convert(holder: ViewHolder, item: Category, position: Int) {
        holder.setOnCheckedChangeListener(R.id.cb_option_tag_name, null)
        holder.setText(R.id.cb_option_tag_name, item.name!!)
        holder.setOnCheckedChangeListener(R.id.cb_option_tag_name,
                object : CompoundButton.OnCheckedChangeListener {
                    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                        Log.d("tag", item.name + ": " + isChecked)
                    }
                })
    }

    fun selectOne(position: Int) {

    }
}

private class TagsRvAdapter(ctx: Context, dataList: List<Tag>?, layoutId: Int)
    : RvCommonAdapter<Tag>(ctx, dataList, layoutId) {
    override fun convert(holder: ViewHolder, item: Tag, position: Int) {
        holder.setText(R.id.cb_option_tag_name, item.name!!)
    }
}