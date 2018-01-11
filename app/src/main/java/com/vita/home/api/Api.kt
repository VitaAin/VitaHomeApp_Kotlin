package com.vita.home.api

import android.content.Context
import com.vita.home.bean.*
import retrofit2.Callback

/**
 *
 * @FileName: com.vita.home.api.Api.java
 * @Author: Vita
 * @Date: 2018-01-08 17:51
 * @Usage:
 */
class Api private constructor(ctx: Context) {

    private var mApiService = ApiService.Factory().createApiService(ctx)

    companion object {
        private var mContext: Context? = null
        fun get(ctx: Context): Api {
            mContext = ctx
            return Instance.api
        }
    }

    private object Instance {
        val api = Api(mContext!!)
    }

    fun login(account: LoginRequest, listener: Callback<Wrap<User>>)
            = mApiService.login(account).enqueue(listener)

    fun getArticles(tagName: String?, listener: Callback<Wrap<Articles>>)
            = mApiService.getArticles(tagName).enqueue(listener)

    fun getArticle(id: Int, listener: Callback<Wrap<Article>>)
            = mApiService.getArticle(id).enqueue(listener)

    fun addArticle(article: Article, listener: Callback<Wrap<Any>>)
            = mApiService.addArticle(article).enqueue(listener)

    fun updateArticle(article: Article, listener: Callback<Wrap<Any>>)
            = mApiService.addArticle(article).enqueue(listener)

    fun getCategories(listener: Callback<Wrap<List<Category>>>)
            = mApiService.getCategories().enqueue(listener)

    fun getTags(listener: Callback<Wrap<List<Tag>>>)
            = mApiService.getTags().enqueue(listener)

    fun getUser(id: Int, listener: Callback<Wrap<User>>)
            = mApiService.getUser(id).enqueue(listener)

    fun getUserArticles(id: Int, listener: Callback<Wrap<List<Article>>>)
            = mApiService.getUserArticles(id).enqueue(listener)

    fun getUserReplies(id: Int, listener: Callback<Wrap<List<Reply>>>)
            = mApiService.getUserReplies(id).enqueue(listener)

    fun getUserLikeArticles(id: Int, listener: Callback<Wrap<List<Article>>>)
            = mApiService.getUserLikeArticles(id).enqueue(listener)

    fun getUserFollowUsers(id: Int, listener: Callback<Wrap<List<User>>>)
            = mApiService.getUserFollowUsers(id).enqueue(listener)
}