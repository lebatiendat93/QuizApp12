package jp.zyyx.favme.repository

import jp.zyyx.favme.ui.auth.AuthApi

class AuthRepository(
    private val api: AuthApi
): BaseRepository() {

   suspend fun login(
        email : String,
        password: String
    )  = safeApiCall {
        api.login(email, password)
    }
}