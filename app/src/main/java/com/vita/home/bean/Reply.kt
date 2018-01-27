package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.home.bean.Reply.java
 * @Author: Vita
 * @Date: 2018-01-11 16:10
 * @Usage:
 */
class Reply {

    var commentable: Commentable? = null
    var content: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null

    class Commentable {
        @SerializedName("comments_count")
        var commentsCount: Int = 0
        var id: Int = 0
        @SerializedName("likes_count")
        var likesCount: Int = 0
        var title: String? = null
    }
}
