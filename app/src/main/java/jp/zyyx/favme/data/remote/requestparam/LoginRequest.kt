package jp.zyyx.favme.data.remote.requestparam

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class LoginRequest(
    val login_id: String,
    val password: String
) :Parcelable