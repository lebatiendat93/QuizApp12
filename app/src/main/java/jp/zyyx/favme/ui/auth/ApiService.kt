package jp.zyyx.favme.ui.auth

import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.responses.LoginResponsesModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponsesModel>
}