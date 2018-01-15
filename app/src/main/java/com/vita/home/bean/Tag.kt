package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.home.bean.Tag.java
 * @Author: Vita
 * @Date: 2018-01-09 14:36
 * @Usage:
 */
class Tag(name: String, description: String) {

    var id: Int = 0
    var name: String? = name
    var description: String? = description
    @SerializedName("articles_count")
    var articlesCount: Int = 0
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
}
