package jp.zyyx.favme.model.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClientFactory(private val okHttpClient: OkHttpClient, private val endpoint:String) {

    fun<T> create(service: Class<T>) : T {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).baseUrl(endpoint).build().create(service)
    }
}