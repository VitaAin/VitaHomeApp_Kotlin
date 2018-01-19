package com.vita.home.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.CompoundButton
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import com.vita.home.R
import com.vita.home.adapter.commonrvadapter.RvCommonAdapter
import com.vita.home.adapter.commonrvadapter.ViewHolder
import com.vita.home.adapter.viewListadapter.ViewListAdapter
import com.vita.home.api.Api
import com.vita.home.bean.*
import com.vita.home.constant.Key
import com.vita.home.dialog.CreateCategoryDialog
import com.vita.home.dialog.CreateTagDialog
import com.vita.home.helper.GlideRequestOpts
import com.vita.home.utils.SystemUtils
import com.vita.home.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_add_article.*
import kotlinx.android.synthetic.main.app_bar_add_article.*
import kotlinx.android.synthetic.main.content_add_article.*
import kotlinx.android.synthetic.main.content_add_article_drawer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AddArticleActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "AddArticleActivity"
    private var mCategories: MutableList<Category> = ArrayList()
    private var mTags: MutableList<Tag> = ArrayList()
    private var mCategoriesSpAdapter: CategoriesSpAdapter? = null
    private var mTagsRvAdapter: TagsRvAdapter? = null
    private var mSelectedCategory: Category? = null
    private var mCoverUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)

        setupViews()
        initData()
    }

    private fun setupViews() {
        setupToolbar()
        setupFab()
        setupDrawer()
    }

    private fun setupToolbar() {
        setSupportActionBar(tb_add_article)

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupFab() =
            fab_in_add_article.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }

    private fun setupDrawer() {
        setupRvsInDrawer()

        mCategoriesSpAdapter = CategoriesSpAdapter(this, mCategories, R.layout.item_category)
        sp_categories.adapter = mCategoriesSpAdapter
        sp_categories.setSelection(0)
        sp_categories.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                mSelectedCategory = null
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i(TAG, "onItemSelected: " + mCategories[position].name)
                mSelectedCategory = mCategories[position]
            }
        }

        tv_add_cover.setOnClickListener(this@AddArticleActivity)
        iv_add_category.setOnClickListener(this@AddArticleActivity)
        iv_add_tag.setOnClickListener(this@AddArticleActivity)
    }

    private fun setupRvsInDrawer() {
        var tagsLM = FlexboxLayoutManager(this)
        tagsLM.flexDirection = FlexDirection.ROW
        tagsLM.flexWrap = FlexWrap.WRAP
        tagsLM.justifyContent = JustifyContent.FLEX_START
        rv_tags.layoutManager = tagsLM

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
                if (response.body()?.status == 1) {
                    mCategories.addAll(response.body()?.data!!)
                    mCategoriesSpAdapter?.notifyDataSetChanged()
                }
            }
        })
        Api.get(this).getTags(object : Callback<Wrap<List<Tag>>> {
            override fun onFailure(call: Call<Wrap<List<Tag>>>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<List<Tag>>>, response: Response<Wrap<List<Tag>>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                if (response.body()?.status == 1) {
                    mTags.addAll(response.body()?.data!!)
                    mTagsRvAdapter?.updateData()
                }
            }
        })
    }

    private fun createArticle() {
        var newArticle = CreateArticleRequest()
        newArticle.title = et_article_title.text.toString()
        if (TextUtils.isEmpty(mCoverUrl)) {
            ToastUtils.showShort(this, getString(R.string.please_upload_cover))
            return
        }
        newArticle.coverUrl = mCoverUrl
        newArticle.body = et_article_body.text.toString()
        if (mSelectedCategory == null) {
            ToastUtils.showShort(this, getString(R.string.please_choose_category))
            return
        }
        newArticle.category = mSelectedCategory?.id!!
        newArticle.tags = mTagsRvAdapter?.selectedTagsId
        Log.d(TAG, "Article done: " + Gson().toJson(newArticle))
        Api.get(this).createArticle(newArticle, object : Callback<Wrap<Article>> {
            override fun onFailure(call: Call<Wrap<Article>>?, t: Throwable?) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<Article>>?, response: Response<Wrap<Article>>?) {
                Log.i(TAG, "onResponse: " + response?.body()?.message)
                if (response?.body()?.status == 1) {
                    ToastUtils.showShort(this@AddArticleActivity, getString(R.string.create_successfully))
                    var intent = Intent(this@AddArticleActivity, ArticleShowActivity::class.java)
                    intent.putExtra(Key.KEY_ARTICLE_ID, response.body()?.data?.id)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }

    private fun uploadCover(uri: Uri) {
        var path = SystemUtils.getImagePath(this, uri)
        // TODO check extension and size of file
        var file = File(path)
        Api.get(this).uploadUserImage(file, object : Callback<Wrap<Image>> {
            override fun onFailure(call: Call<Wrap<Image>>?, t: Throwable?) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<Wrap<Image>>?, response: Response<Wrap<Image>>?) {
                Log.i(TAG, "onResponse: " + response?.body()?.message)
                Log.i(TAG, Gson().toJson(response?.body()))
                if (response?.body()?.status == 1) {
                    mCoverUrl = response.body()?.data?.url!!
                    Log.i(TAG, "cover: " + mCoverUrl)
                    tv_add_cover.visibility = View.GONE
                    iv_cover.visibility = View.VISIBLE
                    Glide.with(this@AddArticleActivity).load(mCoverUrl)
                            .apply(GlideRequestOpts.baseImageOpts)
                            .into(iv_cover)
                }
            }
        })
    }

    private fun showCreateCategoryDialog() {
        var dialog = CreateCategoryDialog(this)
        dialog.show()
        dialog.setOnDataCompletedListener(object : CreateCategoryDialog.OnDataCompletedListener {
            override fun onDataCompleted(category: Category) {
                mCategories.add(category)
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
            if (drawer_add_article.isDrawerOpen(GravityCompat.END)) {
                drawer_add_article.closeDrawer(GravityCompat.END)
            } else {
                drawer_add_article.openDrawer(GravityCompat.END)
            }

    override fun onClick(v: View?) =
            when (v?.id) {
                R.id.tv_add_cover -> SystemUtils.openImage(this, getString(R.string.choose_article_cover))
                R.id.iv_add_category -> showCreateCategoryDialog()
                R.id.iv_add_tag -> showCreateTagDialog()
                else -> {
                    // Do nothing
                }
            }

    override fun onBackPressed() =
            if (drawer_add_article.isDrawerOpen(GravityCompat.END)) {
                drawer_add_article.closeDrawer(GravityCompat.END)
            } else {
                super.onBackPressed()
            }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_article, menu)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SystemUtils.REQUEST_CODE_CHOOSE_IMAGE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val uri: Uri = data?.data!!
                    Log.d(TAG, "onActivityResult: uri: " + uri.path)
                    uploadCover(uri)
                }
            }
        }
    }
}

class CategoriesSpAdapter(ctx: Context, dataList: List<Category>?, layoutId: Int)
    : ViewListAdapter<Category>(ctx, dataList, layoutId) {
    override fun convert(holder: com.vita.home.adapter.viewListadapter.ViewHolder, item: Category) {
        holder.setText(R.id.tv_title, item.name)
        if (!TextUtils.isEmpty(item.description)) {
            holder.setVisibility(R.id.tv_desc, true)
            holder.setText(R.id.tv_desc, item.description)
        } else {
            holder.setVisibility(R.id.tv_desc, false)
        }
    }
}

private class TagsRvAdapter(ctx: Context, dataList: List<Tag>?, layoutId: Int)
    : RvCommonAdapter<Tag>(ctx, dataList, layoutId) {

    var selectedTagsId: MutableList<Int>? = null

    override fun convert(holder: ViewHolder, item: Tag, position: Int) {
        holder.setText(R.id.cb_option_tag_name, item.name!!)
        holder.setOnCheckedChangeListener(R.id.cb_option_tag_name,
                object : CompoundButton.OnCheckedChangeListener {
                    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                        if (selectedTagsId == null) {
                            selectedTagsId = ArrayList()
                        }
                        if (isChecked) {
                            selectedTagsId?.add(item.id)
                        } else {
                            selectedTagsId?.remove(item.id)
                        }
                        Log.d("tag", "selected: " + Gson().toJson(selectedTagsId))
                    }
                })
    }
}