package com.aditya.espressorecord.data

import android.content.Context

class SharedLogin(context: Context) {

    private val USER_NAME: String = "username"
    private val PASSWORD: String = "password"

    private val sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE)

    fun setLogin(userName: String, password: String) {
        sharedPreferences.edit()
            .putString(USER_NAME, userName)
            .putString(PASSWORD, password)
            .apply()
    }

    fun getLogin(): Pair<String, String> {
        val userName = sharedPreferences.getString(USER_NAME, "")
        val password = sharedPreferences.getString(PASSWORD, "")
        return Pair(userName!!, password!!)
    }
}