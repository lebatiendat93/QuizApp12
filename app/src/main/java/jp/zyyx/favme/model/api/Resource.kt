package jp.zyyx.favme.model.api

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class  Success<out T>(val value: T): Resource<T>()
    data class Error(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
    ): Resource<Nothing>()

    class Loading<T>(
        val data: T? = null
    ) : Resource<T>()

}