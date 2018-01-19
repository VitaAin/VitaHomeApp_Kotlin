package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.home.bean.Article.java
 * @Author: Vita
 * @Date: 2018-01-09 13:53
 * @Usage:
 */
class CreateArticleRequest {

    var title: String? = null
    @SerializedName("cover_url")
    var coverUrl: String? = null
    var body: String? = null
    @SerializedName("user_id")
    var userId: Int? = null
    var category: Int? = null // only id of category
    @SerializedName("is_public")
    var isPublic: Boolean = false
    var tags: List<Int>? = null// only id of tag
}
