package jp.zyyx.favme.data.remote.responses.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class ExamDetailResponses(
    val statusCode: Int,
    val result: List<ExamDetailResult> ,
    val message: String? = null
) : Parcelable

@Parcelize
@Keep
data class ExamDetailResult(
    val id: Int,
    val title: String,
    val list_exam: List<ExamDetailSubject>,
) : Parcelable


@Parcelize
@Keep
data class ExamDetailSubject(
    val id: Int,
    val title: String
) : Parcelable