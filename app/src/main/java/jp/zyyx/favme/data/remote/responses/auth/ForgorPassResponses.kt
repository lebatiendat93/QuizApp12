package jp.zyyx.favme.data.remote.responses.auth

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
@Parcelize
@Keep
data class ForgotPassResponse(
    val statusCode: Int,
    val result: Boolean,
    val message: String?= null
): Parcelable

