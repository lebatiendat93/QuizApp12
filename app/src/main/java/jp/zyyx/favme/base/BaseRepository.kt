package jp.zyyx.favme.base

import android.util.Log
import jp.zyyx.favme.model.api.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Log.e("Success", "API Success")
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {

                    is HttpException -> {
                        Log.e("Failed", "API ${throwable.response()?.errorBody()}")
                        Resource.Error(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Error(true, null, null)
                    }
                }
            }
        }
    }
}