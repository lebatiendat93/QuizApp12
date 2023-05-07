package jp.zyyx.favme.data.local

import android.content.Context
import android.content.SharedPreferences

object MySharePreference {
    private lateinit var instance: MySharePreference
    private lateinit var sharedPreferences: SharedPreferences

    private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    private const val SAVE_USER = "SAVE_USER"
    private const val FIRST_START_APP = "FIRST_START_APP"

    fun init(context: Context) {
        instance = MySharePreference
        sharedPreferences = context.getSharedPreferences("QUIZ-PREFERENCE", Context.MODE_PRIVATE)
    }


    fun getInstance(): MySharePreference {
        return instance
    }

    fun setFirstStartApp(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(FIRST_START_APP, isFirst)
        editor.apply()
    }

    fun getFirstStartApp(): Boolean {
        return sharedPreferences.getBoolean(FIRST_START_APP, false)
    }

    fun setAccessToken(accessToken: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(): String {
        return sharedPreferences.getString(ACCESS_TOKEN, "") ?: ""
    }

    fun setSavePass(currentNameUser: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(SAVE_USER, currentNameUser)
        editor.apply()
    }

    fun getPass(): String {
        return sharedPreferences.getString(SAVE_USER, "") ?: ""
    }

    fun isLogin(): Boolean {
        return getAccessToken().isNotEmpty()
    }

    fun isCbSavePass(): Boolean {
        return getPass().isNotEmpty()
    }
}