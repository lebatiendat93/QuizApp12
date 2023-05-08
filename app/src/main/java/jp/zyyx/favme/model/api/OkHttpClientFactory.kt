package jp.zyyx.favme.model.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpClientFactory(accessToken: String? = null) {

    companion object {
        private var mAccessToken: String? = null
    }

    init {
        mAccessToken = accessToken
    }

    fun create(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Logger.printDebug("retrofit $message")
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val defaultClientBuilder = OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                if (mAccessToken != null) {
                    requestBuilder.header("authorization", mAccessToken!!)
                }

                chain.proceed(requestBuilder.build())
            }
        return defaultClientBuilder.build()

    }
}