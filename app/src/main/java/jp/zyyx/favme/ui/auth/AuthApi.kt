package jp.zyyx.favme.ui.auth

import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.data.remote.responses.LoginResponses
import jp.zyyx.favme.data.remote.responses.RegisterResponses
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("/login")
    suspend fun login(
        @Body login : LoginRequest
    ): LoginResponses

    @POST("/register")
    suspend fun register(
        @Body register: RegisterRequest
    ): RegisterResponses


}