package com.vita.home.helper

import android.content.Context
import com.google.gson.Gson
import com.vita.home.bean.User

import com.vita.home.constant.Key
import com.vita.home.utils.SPUtils

/**
 * @FileName: com.vita.home.helper.AccountHelper.java
 * @Author: Vita
 * @Date: 2018-01-11 15:02
 * @Usage:
 */
object AccountHelper {

    fun check(ctx: Context): Boolean
            = getUserId(ctx) != 0

    fun getUserId(ctx: Context): Int
            = SPUtils.get(ctx, Key.KEY_USER_ID, 0) as Int

    fun getUser(ctx: Context): User {
        var userJson = SPUtils.get(ctx, Key.KEY_USER, "") as String
        return Gson().fromJson(userJson, User::class.java)
    }

    fun getUserJson(ctx: Context): String
            = SPUtils.get(ctx, Key.KEY_USER, "") as String

    fun getToken(ctx: Context): String
            = SPUtils.get(ctx, Key.KEY_TOKEN, "") as String

    fun updateUser(ctx: Context, new: User) {
        var user = getUser(ctx)
        user.realName = new.realName
        user.sex = new.sex
        user.phone = new.phone
        user.qq = new.qq
        user.city = new.city
        user.introduction = new.introduction
        SPUtils.remove(ctx, Key.KEY_USER)
        SPUtils.put(ctx, Key.KEY_USER, Gson().toJson(user))
    }
}
