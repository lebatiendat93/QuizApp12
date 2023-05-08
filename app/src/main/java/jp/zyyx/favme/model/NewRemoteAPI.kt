//package jp.zyyx.favme.model
//
//import androidx.annotation.Keep
//import com.google.gson.GsonBuilder
//import jp.zyyx.favme.BuildConfig
//import jp.zyyx.favme.ui.auth.ApiService
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//@Keep
//object NewRemoteAPI {
//    private const val BASE_URL =
//        "https://asia-northeast1-quiz-app-traning.cloudfunctions.net"
//
//    private val gson = GsonBuilder()
//        .setLenient()
//        .create()
//
//    val retrofit = Retrofit.Builder()
////        .client(
////            OkHttpClient.Builder().also { client ->
////                if (BuildConfig.DEBUG) {
////                    val logging =
////                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
////                    client.addInterceptor(logging)
////                }
////            }.build()
////        )
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()
//        .create(ApiService::class.java)
//}
//
