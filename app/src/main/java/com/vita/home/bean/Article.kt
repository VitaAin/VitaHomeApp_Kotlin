package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.home.bean.Article.java
 * @Author: Vita
 * @Date: 2018-01-09 13:53
 * @Usage:
 */
class Article {

    var id: Int = 0
    var title: String? = null
    @SerializedName("cover_url")
    var coverUrl: String? = null
    var body: String? = null
    @SerializedName("user_id")
    var userId: Int = 0
    @SerializedName("last_comment_user_id")
    var lastCommentUserId: Int = 0
    @SerializedName("category_id")
    var categoryId: Int = 0
    @SerializedName("view_count")
    var viewCount: Int = 0
    @SerializedName("comments_count")
    var commentsCount: Int = 0
    @SerializedName("likes_count")
    var likesCount: Int = 0
    @SerializedName("close_comment")
    var isCloseComment: Boolean = false
    @SerializedName("is_public")
    var isPublic: Boolean = false
    @SerializedName("is_top")
    var isTop: Boolean = false
    @SerializedName("is_excellent")
    var isExcellent: Boolean = false
    @SerializedName("last_comment_time")
    var lastCommentTime: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    var user: User? = null
    var category: Category? = null
    var tags: List<Tag>? = null
}
