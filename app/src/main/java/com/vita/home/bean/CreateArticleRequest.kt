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
    var userId: Int = 0
    @SerializedName("category_id")
    var categoryId: Int = 0
    @SerializedName("is_public")
    var isPublic: Boolean = false
    var tags: List<Int>? = null// only id of tag
}
