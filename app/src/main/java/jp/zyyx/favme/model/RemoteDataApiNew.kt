package jp.zyyx.favme.model

import androidx.annotation.Keep
import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.data.remote.responses.LoginResponses
import jp.zyyx.favme.data.remote.responses.RegisterResponses
import jp.zyyx.favme.model.api.APIClientFactory
import jp.zyyx.favme.model.api.OkHttpClientFactory
import retrofit2.http.Body
import retrofit2.http.POST

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




}


//companion object {
//    private const val API_ENDPOINT =
//        "https://asia-northeast1-quiz-app-traning.cloudfunctions.net"
//
//    private var quizClient: QuizAPI = createAPIInstance()
//
//    val instance: QuizAPI
//        get() = quizClient
//
//    fun accessToken(accessToken: String) {
//        quizClient = createAPIInstance(accessToken)
//    }
//
//    fun clearAccessToken() {
//        quizClient = createAPIInstance()
//    }
//
//    private fun createAPIInstance(accessToken: String? = null): QuizAPI {
//        return APIClientFactory(OkHttpClientFactory(accessToken).create(), API_ENDPOINT).create(
//            QuizAPI::class.java
//        )
//    }
//}