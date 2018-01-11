package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.home.bean.Image.java
 * @Author: Vita
 * @Date: 2018-01-09 15:21
 * @Usage:
 */
class Image {
    var id: Int = 0
    @SerializedName("user_id")
    var userId: Int = 0
    var uid: String? = null
    var name: String? = null
    var url: String? = null
    var size: Int = 0
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
}
