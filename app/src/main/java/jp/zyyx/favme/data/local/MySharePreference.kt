package jp.zyyx.favme.data.local

import android.content.Context
import android.content.SharedPreferences

object MySharePreference {
    private lateinit var instance: MySharePreference
    private lateinit var sharedPreferences: SharedPreferences

    private const val ACCESS_TOKEN = "ACCESS_TOKEN"

    fun init(context: Context) {
        instance = MySharePreference
        sharedPreferences = context.getSharedPreferences("QUIZ-PREFERENCE", Context.MODE_PRIVATE)
    }

    fun getInstance(): MySharePreference {
        return instance
    }

    fun setAccessToken(accessToken: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(): String {
        return sharedPreferences.getString(ACCESS_TOKEN, "") ?: ""
    }
}