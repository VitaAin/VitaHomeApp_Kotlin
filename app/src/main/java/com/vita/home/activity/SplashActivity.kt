package com.vita.home.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils

import com.vita.home.R
import com.vita.home.utils.SPUtils

class SplashActivity : AppCompatActivity() {

    private var mHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        checkToken()
    }

    private fun checkToken() {
        val userCache: String = SPUtils.get(this, "User", "") as String
        val token: String = SPUtils.get(this, "Token", "") as String
        var clz: Class<*> = MainActivity::class.java
        if (TextUtils.isEmpty(userCache) || TextUtils.isEmpty(token)) {
            clz = LoginActivity::class.java
        }
        jumpTo(clz)
    }

    private fun jumpTo(cls: Class<*>) {
        val delaySecond: Long = 2 * 1000
        mHandler.postDelayed({
            startActivity(Intent(this@SplashActivity, cls))
        }, delaySecond)
    }
}
