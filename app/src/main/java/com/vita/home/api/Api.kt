package com.vita.home.api

import com.vita.home.bean.*
import retrofit2.Callback

/**
 *
 * @FileName: com.vita.home.api.Api.java
 * @Author: Vita
 * @Date: 2018-01-08 17:51
 * @Usage:
 */
class Api private constructor() {

    private var mApiService = ApiService.Factory().createApiService()

    companion object {
        fun get(): Api = Instance.api
    }

    private object Instance {
        val api = Api()
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
}