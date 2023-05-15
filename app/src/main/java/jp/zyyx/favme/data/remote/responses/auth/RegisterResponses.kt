package jp.zyyx.favme.data.remote.responses.auth

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class RegisterResponses(
    val statusCode: Int,
    val result: ResultRegister,
    val message: String? = null
) : Parcelable

@Parcelize
@Keep
data class ResultRegister(
    val access_token: String,
    val user_id: Int,
) : Parcelable