package jp.zyyx.favme.model


sealed class ResourceNew<out T> {
    object Loading : ResourceNew<Nothing>()

    data class Success<T>(val data: T) : ResourceNew<T>()

    data class Error(val error: ModelException) : ResourceNew<Nothing>()

    object Empty : ResourceNew<Nothing>()
}