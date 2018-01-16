package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.home.bean.User.java
 * @Author: Vita
 * @Date: 2018-01-09 14:33
 * @Usage:
 */
class User {
    var id: Int = 0
    @SerializedName("github_id")
    var githubId: Int = 0
    var name: String? = null
    var email: String? = null
    var avatar: String? = null
    @SerializedName("real_name")
    var realName: String? = null
    var sex: String? = null
    var phone: String? = null
    var qq: String? = null
    var city: String? = null
    var introduction: String? = null
    @SerializedName("articles_count")
    var articlesCount: Int = 0
    @SerializedName("comments_count")
    var commentsCount: Int = 0
    @SerializedName("images_count")
    var imagesCount: Int = 0
    @SerializedName("words_count")
    var wordsCount: Int = 0
    @SerializedName("likes_count")
    var likesCount: Int = 0
    @SerializedName("followers_count")
    var followersCount: Int = 0
    @SerializedName("followings_count")
    var followingsCount: Int = 0
    @SerializedName("is_banned")
    var isBanned: Int = 0
    @SerializedName("confirm_code")
    var confirmCode: String? = null
    @SerializedName("is_confirmed")
    var isConfirmed: Int = 0
    @SerializedName("last_actived_at")
    var lastActivedAt: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("deleted_at")
    var deletedAt: String? = null

    @SerializedName("jwt_token")
    var jwtToken: JwtToken? = null

    class JwtToken {
        @SerializedName("access_token")
        var accessToken: String? = null
        @SerializedName("expires_in")
        var expiresIn: Long? = null
        @SerializedName("token_type")
        var tokenType: String? = null
    }
}
