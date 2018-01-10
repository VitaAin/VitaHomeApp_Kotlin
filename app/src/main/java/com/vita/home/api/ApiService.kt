package com.vita.home.api

import com.vita.home.bean.*
import okhttp3.OkHttpClient
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

    @GET("articles")
    fun getArticles(@Query("tag") tagName: String?): Call<Wrap<Articles>>

    @GET("articles/{id}")
    fun getArticle(@Path("id") id: Int): Call<Wrap<Article>>

    @POST("articles")
    fun addArticle(@Body() article: Article): Call<Wrap<Any>>

    @PUT("articles/{id}")
    fun updateArticle(article: Article): Call<Wrap<Any>>

    @GET("categories")
    fun getCategories(): Call<Wrap<List<Category>>>

    @GET("tags")
    fun getTags(): Call<Wrap<List<Tag>>>

    @GET("users/{id}/articles")
    fun getUserArticles(@Path("id") id: Int): Call<Wrap<Articles>>

    class Factory {
        fun createApiService(): ApiService {
            val client = OkHttpClient.Builder()
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    // 这里可以拦截以添加公共参数
//                    .addInterceptor(object : Interceptor {
//                        override fun intercept(chain: Interceptor.Chain?): Response {
//                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                        }
//                    })
                    .build()

            val retrofit = Retrofit.Builder().baseUrl(API_ROOT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}