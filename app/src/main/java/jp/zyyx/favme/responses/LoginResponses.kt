package jp.zyyx.favme.responses

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
class LoginResponses(
    val statusCode: String,
    val result: Result,
    val message: String?= null
) : Parcelable


@Keep
@Parcelize
class Result(
    val access_token: String,
    val user_id: Int,
    val status: Int? = null ,
    val name: String? = null,
) : Parcelable