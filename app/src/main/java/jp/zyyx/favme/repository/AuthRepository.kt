package jp.zyyx.favme.repository

import jp.zyyx.favme.base.BaseRepository
import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.ui.auth.AuthApi

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(
        loginParam: LoginRequest
    ) = safeApiCall {
        api.login(loginParam)
    }

    suspend fun register(registerRequest: RegisterRequest)
    = safeApiCall {
        api.register(registerRequest)
    }
}