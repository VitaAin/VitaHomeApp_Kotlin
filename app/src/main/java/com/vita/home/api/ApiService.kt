package com.vita.home.api

import android.content.Context
import com.vita.home.bean.*
import com.vita.home.helper.AccountHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

import java.util.concurrent.TimeUnit

val API_ROOT: String = "http://api.vitain.top/api/v1/"

/**
 * @FileName: com.vita.home.api.ApiService.java
 * @Author: Vita
 * @Date: 2018-01-08 15:50
 * @Usage:
 */
interface ApiService {

    @POST("user/register")
    fun register(): Call<Wrap<Any>>

    @POST("user/login")
    fun login(@Body() account: LoginRequest): Call<Wrap<User>>

    @GET("user/logout")
    fun logout(): Call<Wrap<Any>>

    @GET("articles")
    fun getArticles(@Query("page") page: Int, @Query("tag") tagName: String?): Call<Wrap<Articles>>

    @GET("articles/{id}")
    fun getArticle(@Path("id") id: Int): Call<Wrap<Article>>

    @POST("articles")
    fun createArticle(@Body() article: CreateArticleRequest): Call<Wrap<Article>>

    @PUT("articles/{id}")
    fun updateArticle(article: Article): Call<Wrap<Any>>

    @GET("categories")
    fun getCategories(): Call<Wrap<List<Category>>>

    @POST("categories")
    fun createCategory(@Body() category: Category): Call<Wrap<Category>>

    @GET("tags")
    fun getTags(): Call<Wrap<List<Tag>>>

    @POST("tags")
    fun createTag(@Body() tag: Tag): Call<Wrap<Tag>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<Wrap<User>>

    @GET("users/{id}/articles")
    fun getUserArticles(@Path("id") id: Int): Call<Wrap<List<Article>>>

    @GET("users/{id}/replies")
    fun getUserReplies(@Path("id") id: Int): Call<Wrap<List<Reply>>>

    @GET("users/{id}/like_articles")
    fun getUserLikeArticles(@Path("id") id: Int): Call<Wrap<List<Article>>>

    @GET("users/{id}/follow_users")
    fun getUserFollowUsers(@Path("id") id: Int): Call<Wrap<List<User>>>

    @GET("users/{id}/images")
    fun getUserImages(@Path("id") id: Int): Call<Wrap<List<Image>>>

    @POST("edit_user_info")
    fun editUserInfo(@Body() user: User): Call<Wrap<User>>

    @GET("articles/{id}/likes")
    fun getArticleLikes(@Path("id") id: Int): Call<Wrap<Any>>

    @GET("articles/{id}/comments")
    fun getArticleComments(@Path("id") id: Int): Call<Wrap<Any>>

    @POST("comments")
    fun createComment(@Body() comment: Comment): Call<Wrap<Any>>

    @GET("article/is_like")
    fun isLikeOrNot(@Query("id") id: Int): Call<Wrap<IsLike>>

    @GET("article/like")
    fun like(@Query("id") id: Int): Call<Wrap<IsLike>>

    @GET("user/is_follow")
    fun isFollowOrNot(@Query("id") id: Int): Call<Wrap<IsFollow>>

    @GET("user/follow")
    fun follow(@Query("id") id: Int): Call<Wrap<IsFollow>>

    @GET("notifications")
    fun getNotifications(): Call<Wrap<List<Notification>>>

    @GET("notice_reply")
    fun getNoticeReply(): Call<Wrap<List<Notification>>>

    @GET("notice_follow")
    fun getNoticeFollow(): Call<Wrap<List<Notification>>>

    @GET("notice_like")
    fun getNoticeLike(): Call<Wrap<List<Notification>>>

    class Factory {
        fun createApiService(ctx: Context): ApiService {
            val client = OkHttpClient.Builder()
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    // 这里可以拦截以添加公共参数
                    .addInterceptor { chain ->
                        val request = chain.request()
                        if (AccountHelper.check(ctx)) {
                            val requestNew = request.newBuilder()
                                    .header("Accept", "application/json")
                                    .header("Authorization", "Bearer " + AccountHelper.getToken(ctx))
//                                    .method(request.method(), request.body())
                                    .build()
                            chain.proceed(requestNew)
                        } else {
                            chain.proceed(request)
                        }
                    }
                    .build()

            val retrofit = Retrofit.Builder().baseUrl(API_ROOT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}