package jp.zyyx.favme.model

import androidx.annotation.Keep
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Keep
class RemoteDataSource {
    companion object {
        private const val BASE_URL =
            "https://asia-northeast1-quiz-app-traning.cloudfunctions.net"
    }
    fun <Api> buildAPI(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(api)
    }
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