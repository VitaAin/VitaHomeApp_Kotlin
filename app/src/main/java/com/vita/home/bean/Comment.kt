package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.home.bean.Comment.java
 * @Author: Vita
 * @Date: 2018-01-09 14:38
 * @Usage:
 */
class Comment {

    var id: Int = 0
    @SerializedName("user_id")
    var userId: Int = 0
    var content: String? = null
    @SerializedName("commentable_id")
    var commentableId: Int = 0
    @SerializedName("commentable_type")
    var commentableType: String? = null
    @SerializedName("parent_id")
    var parentId: Int = 0
    var level: Int = 0
    var floor: Int = 0
    @SerializedName("children_count")
    var childrenCount: Int = 0
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
}
