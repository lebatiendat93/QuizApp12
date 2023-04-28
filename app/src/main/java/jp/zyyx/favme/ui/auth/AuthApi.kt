package jp.zyyx.favme.ui.auth

import jp.zyyx.favme.responses.LoginResponses
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("/login")
    suspend fun login(
        @Query("login_id") login_id: String,
        @Query("password") password: String
    ): LoginResponses




}