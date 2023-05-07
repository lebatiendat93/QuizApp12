package jp.zyyx.favme.data.remote.requestparam

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


data class RegisterRequest(
    val email: String,
    val name: String,
    val phone_number: String,
    val birthday: String,
    val password: String,
)