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

    fun register(listener: Callback<Wrap<Any>>)
            = mApiService.register().enqueue(listener)

    fun login(account: LoginRequest, listener: Callback<Wrap<User>>)
            = mApiService.login(account).enqueue(listener)

    fun logout(listener: Callback<Wrap<Any>>)
            = mApiService.logout().enqueue(listener)

    fun getArticles(page: Int = 1, tagName: String?, listener: Callback<Wrap<Articles>>)
            = mApiService.getArticles(page, tagName).enqueue(listener)

    fun getArticle(id: Int, listener: Callback<Wrap<Article>>)
            = mApiService.getArticle(id).enqueue(listener)

    fun createArticle(article: CreateArticleRequest, listener: Callback<Wrap<Article>>)
            = mApiService.createArticle(article).enqueue(listener)

    fun updateArticle(article: Article, listener: Callback<Wrap<Any>>)
            = mApiService.updateArticle(article).enqueue(listener)

    fun getCategories(listener: Callback<Wrap<List<Category>>>)
            = mApiService.getCategories().enqueue(listener)

    fun createCategory(category: Category, listener: Callback<Wrap<Category>>)
            = mApiService.createCategory(category).enqueue(listener)

    fun getTags(listener: Callback<Wrap<List<Tag>>>)
            = mApiService.getTags().enqueue(listener)

    fun createTag(tag: Tag, listener: Callback<Wrap<Tag>>)
            = mApiService.createTag(tag).enqueue(listener)

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

    fun editUserInfo(user: User, listener: Callback<Wrap<User>>)
            = mApiService.editUserInfo(user).enqueue(listener)

    fun isLikeOrNot(id: Int, listener: Callback<Wrap<IsLike>>)
            = mApiService.isLikeOrNot(id).enqueue(listener)

    fun like(id: Int, listener: Callback<Wrap<IsLike>>)
            = mApiService.like(id).enqueue(listener)

    fun isFollowOrNot(id: Int, listener: Callback<Wrap<IsFollow>>)
            = mApiService.isFollowOrNot(id).enqueue(listener)

    fun follow(id: Int, listener: Callback<Wrap<IsFollow>>)
            = mApiService.follow(id).enqueue(listener)

    fun getNotifications(listener: Callback<Wrap<List<Notification>>>)
            = mApiService.getNotifications().enqueue(listener)

    fun getNoticeReply(listener: Callback<Wrap<List<Notification>>>)
            = mApiService.getNoticeReply().enqueue(listener)

    fun getNoticeFollow(listener: Callback<Wrap<List<Notification>>>)
            = mApiService.getNoticeFollow().enqueue(listener)

    fun getNoticeLike(listener: Callback<Wrap<List<Notification>>>)
            = mApiService.getNoticeLike().enqueue(listener)
}