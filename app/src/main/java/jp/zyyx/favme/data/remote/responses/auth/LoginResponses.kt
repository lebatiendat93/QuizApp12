package jp.zyyx.favme.data.remote.responses.auth

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
@Parcelize
@Keep
data class LoginResponses(
    val statusCode: Int,
    val result: ResultLogin,
    val message: String?= null
): Parcelable


@Parcelize
@Keep
data class ResultLogin(
    val access_token: String,
    val user_id: Int,
    val status: Int? = null ,
    val name: String? = null,
) : Parcelable