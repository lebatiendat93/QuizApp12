package jp.zyyx.favme.model

import androidx.annotation.Keep
import jp.zyyx.favme.data.remote.requestparam.GetDepartmentRequest
import jp.zyyx.favme.data.remote.requestparam.ListDepartmentInfoRequest
import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.data.remote.responses.GetDepartmentResponses
import jp.zyyx.favme.data.remote.responses.ListDepartmentInfoResponses
import jp.zyyx.favme.data.remote.responses.LoginResponses
import jp.zyyx.favme.data.remote.responses.RegisterResponses
import jp.zyyx.favme.model.api.APIClientFactory
import jp.zyyx.favme.model.api.OkHttpClientFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

@Keep
interface RemoteDataApiNew {
    companion object {
        private const val API_ENDPOINT = "https://asia-northeast1-quiz-app-traning.cloudfunctions.net"

        private var remoteDataAPINew: RemoteDataApiNew = createApiInstance()

        val instance: RemoteDataApiNew
            get() = remoteDataAPINew

        fun applyAccessToken(accessToken: String) {
            remoteDataAPINew = createApiInstance(accessToken)
        }

        fun clearAccessToken() {
            remoteDataAPINew = createApiInstance()
        }

        private fun createApiInstance(accessToken: String? = null): RemoteDataApiNew {
            return APIClientFactory(OkHttpClientFactory(accessToken).create(), API_ENDPOINT).create(
                RemoteDataApiNew::class.java
            )
        }
    }

    @POST("/login")
    suspend fun login(
        @Body login : LoginRequest
    ):  LoginResponses

    @POST("/register")
    suspend fun register(
        @Body register: RegisterRequest
    ): RegisterResponses


    @POST("/getDepartmentList")
    suspend fun getDepartmentList(
        @Header ("Authorization") header: String,
        @Body getDepartmentRequest : GetDepartmentRequest
    ): GetDepartmentResponses

    @POST("/listDepartmentInfo")
    suspend fun listDepartmentInfo(
        @Header ("Authorization") header: String,
        @Body listDepartmentInfoRequest : ListDepartmentInfoRequest
    ): ListDepartmentInfoResponses



}


