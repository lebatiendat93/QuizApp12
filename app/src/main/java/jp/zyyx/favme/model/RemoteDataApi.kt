package jp.zyyx.favme.model

import androidx.annotation.Keep
import jp.zyyx.favme.data.remote.requestparam.auth.ForgotPassRequest
import jp.zyyx.favme.data.remote.requestparam.auth.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.auth.RegisterRequest
import jp.zyyx.favme.data.remote.requestparam.home.*
import jp.zyyx.favme.data.remote.responses.auth.ForgotPassResponse
import jp.zyyx.favme.data.remote.responses.auth.LoginResponses
import jp.zyyx.favme.data.remote.responses.auth.RegisterResponses
import jp.zyyx.favme.data.remote.responses.home.*
import jp.zyyx.favme.model.api.APIClientFactory
import jp.zyyx.favme.model.api.OkHttpClientFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

@Keep
interface RemoteDataApi {
    companion object {
        private const val API_ENDPOINT = "https://asia-northeast1-quiz-app-traning.cloudfunctions.net"

        private var remoteDataAPINew: RemoteDataApi = createApiInstance()

        val instance: RemoteDataApi
            get() = remoteDataAPINew

        fun applyAccessToken(accessToken: String) {
            remoteDataAPINew = createApiInstance(accessToken)
        }

        fun clearAccessToken() {
            remoteDataAPINew = createApiInstance()
        }

        private fun createApiInstance(accessToken: String? = null): RemoteDataApi {
            return APIClientFactory(OkHttpClientFactory(accessToken).create(), API_ENDPOINT).create(
                RemoteDataApi::class.java
            )
        }
    }

    @POST("/login")
    suspend fun login(
        @Body login : LoginRequest
    ): LoginResponses

    @POST("/register")
    suspend fun register(
        @Body register: RegisterRequest
    ): RegisterResponses


    @POST("/forgotPassword")
    suspend fun forgotPassword(
        @Body forgotPassRequest: ForgotPassRequest
    ): ForgotPassResponse

    @POST("/getDepartmentList")
    suspend fun getDepartmentList(
        @Header ("Authorization") header: String,
        @Body getDepartmentRequest : GetDepartmentRequest
    ): GetDepartmentResponses

    @POST("/listDepartmentInfo")
    suspend fun getListDepartmentInfo(
        @Header ("Authorization") header: String,
        @Body listDepartmentInfoRequest : ListDepartmentInfoRequest
    ): ListDepartmentInfoResponses

    @POST("/listExam")
    suspend fun getListExam(
        @Header ("Authorization") header: String,
        @Body examRequest : ExamRequest
    ): ExamResponses

    @POST("/examDetail")
    suspend fun getExamDetail(
        @Header ("Authorization") header: String,
        @Body examDetailRequest : ExamDetailRequest
    ): ExamDetailResponses

    @POST("/searchSubject")
    suspend fun searchSubject(
        @Header ("Authorization") header: String,
        @Body searchSubjectRequest : SearchSubjectRequest
    ): SearchSubjectResponses


}


