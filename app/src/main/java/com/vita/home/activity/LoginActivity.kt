package com.vita.home.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.support.v7.app.AppCompatActivity

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView

import com.vita.home.R

import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import com.vita.home.api.Api
import com.vita.home.bean.LoginRequest
import com.vita.home.bean.User
import com.vita.home.bean.Wrap
import com.vita.home.constant.Key
import com.vita.home.utils.SPUtils
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    private val TAG: String = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_password.setOnEditorActionListener(TextView.OnEditorActionListener { textView, id, keyEvent ->
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        btn_sign_in.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        // Reset errors.
        et_email.error = null
        et_password.error = null

        // Store values at the time of the login attempt.
        val email = et_email.text.toString()
        val password = et_password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            et_password.error = getString(R.string.error_invalid_password)
            focusView = et_password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            et_email.error = getString(R.string.error_field_required)
            focusView = et_email
            cancel = true
        } else if (!isEmailValid(email)) {
            et_email.error = getString(R.string.error_invalid_email)
            focusView = et_email
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        btn_sign_in.isClickable = false
        val account = LoginRequest(email, password)
        Api.get(this).login(account, object : Callback<Wrap<User>> {
            override fun onResponse(call: Call<Wrap<User>>, response: Response<Wrap<User>>) {
                Log.i(TAG, "onResponse: " + response.body()?.message)
                loginFinished()
                if (response.body()?.status == 1) {
                    loginSuccessfully(response.body()?.data!!)
                }
            }

            override fun onFailure(call: Call<Wrap<User>>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.toString())
                loginFinished()
            }
        })
    }

    private fun loginFinished() {
        btn_sign_in.isClickable = true
        showProgress(false)
    }

    private fun loginSuccessfully(user: User) {
        SPUtils.put(this, Key.KEY_USER_ID, user.id)
        SPUtils.put(this, Key.KEY_USER, Gson().toJson(user))
        SPUtils.put(this, Key.KEY_TOKEN, user.jwtToken!!.accessToken)
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }

    private fun isEmailValid(email: String)
            = email.contains("@") && email.endsWith(".com")

    private fun isPasswordValid(password: String)
            = password.length >= 6

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    login_form.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            pb_login.visibility = if (show) View.VISIBLE else View.GONE
            pb_login.animate().setDuration(shortAnimTime.toLong()).alpha(
                    (if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pb_login.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            pb_login.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }
}

