package jp.zyyx.favme.model

import androidx.annotation.Keep
import com.google.gson.GsonBuilder
import jp.zyyx.favme.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Keep
class RemoteDataAPI {
    companion object {
        private const val BASE_URL =
            "https://asia-northeast1-quiz-app-traning.cloudfunctions.net"
    }

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    fun <Api> buildAPI(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().also { client ->
                    if (BuildConfig.DEBUG) {
                        val logging =
                            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
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