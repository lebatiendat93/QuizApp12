package jp.zyyx.favme.data.remote.requestparam.auth

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class ForgotPassRequest(
    val email: String
) :Parcelable