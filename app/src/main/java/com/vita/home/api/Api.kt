package com.vita.home.api

import com.vita.home.bean.Article
import com.vita.home.bean.Articles
import com.vita.home.bean.Wrap
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

    fun getArticles(listener: Callback<Wrap<Articles>>)
            = mApiService.getArticles().enqueue(listener)

    fun getArticle(id: Int, listener: Callback<Wrap<Article>>)
            = mApiService.getArticle(id).enqueue(listener)

}