package jp.zyyx.favme.repository

import android.util.Log
import jp.zyyx.favme.model.api.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("Success", "API Success")
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Log.d("Failed", "API ${throwable.response()?.errorBody()}")
                        Resource.Failure(true, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}