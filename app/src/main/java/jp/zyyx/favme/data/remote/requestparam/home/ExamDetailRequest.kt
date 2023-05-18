package jp.zyyx.favme.data.remote.requestparam.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class ExamRequest(
    val user_id: Int,
    val subject_id: Int,
    val type: Int,
    val sort_field: Int,
    val sort_by: String,
) : Parcelable