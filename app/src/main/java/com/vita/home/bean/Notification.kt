package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.home.bean.Notification.java
 * @Author: Vita
 * @Date: 2018-01-17 16:06
 * @Usage:
 */
class Notification {

    var id: String? = null
    var type: String? = null
    var data: Data? = null
    @SerializedName("notifiable_id")
    var notifiableId: Int = 0
    @SerializedName("notifiable_type")
    var notifiableType: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null

    class Data {
        var comment: String? = null
        var name: String? = null
        var title: String? = null
        @SerializedName("title_id")
        var titleId: Int = 0
        @SerializedName("user_id")
        var userId: Int = 0
    }
}
