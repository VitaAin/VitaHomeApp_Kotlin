package com.vita.home.bean

import com.google.gson.annotations.SerializedName

/**
 * @FileName: com.vita.testdagger2.Articles.Articles.java
 * @Author: Vita
 * @Date: 2017-12-21 10:43
 * @Usage:
 */
class Articles {

    var status: Int = 0
    var message: String? = null
    var data: DataBean? = null

    class DataBean {
        @SerializedName("current_page")
        var currentPage: Int = 0
        @SerializedName("first_page_url")
        var firstPageUrl: String? = null
        var from: Int = 0
        @SerializedName("last_page")
        var lastPage: Int = 0
        @SerializedName("last_page_url")
        var lastPageUrl: String? = null
        @SerializedName("next_page_url")
        var nextPageUrl: String? = null
        var path: String? = null
        @SerializedName("per_page")
        var perPage: Int = 0
        @SerializedName("prev_page_url")
        var prevPageUrl: Any? = null
        var to: Int = 0
        var total: Int = 0
        var data: List<Article>? = null

    }
}
