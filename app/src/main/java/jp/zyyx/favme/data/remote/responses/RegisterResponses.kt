package jp.zyyx.favme.data.remote.responses

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize


data class RegisterResponses(
    val statusCode: Int,
    val result: ResultRegister,
    val message: String?= null
)

data class ResultRegister(
    val access_token: String,
    val user_id: Int,
)