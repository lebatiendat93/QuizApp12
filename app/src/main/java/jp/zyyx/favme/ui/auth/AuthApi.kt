package jp.zyyx.favme.ui.auth

import jp.zyyx.favme.responses.LoginResponses
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthApi {

    @POST("/login")
    suspend fun login(
        @Field("login_id") login_id: String,
        @Field("password") password: String
    ): LoginResponses




}