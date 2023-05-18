package jp.zyyx.favme.data.remote.responses.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class ExamResponses(
    val statusCode: Int,
    val result: ExamResult,
    val message: String? = null
) : Parcelable

@Parcelize
@Keep
data class ExamResult(
    val id: Int,
    val title: String,
    val department_description: String,
    val department_id: Int,
    val department_title: String,
    val description: String,
    val list_exam: List<ListExam>,
) : Parcelable


@Parcelize
@Keep
data class ListExam(
    val id: Int,
    val title: String,
    val image: String,
    val author_email: String,
    val author_id: Int,
    val author_name: String,
    val number: Int,
    val saved_num: Int,
    val status: Int,
    val time: Int,
) : Parcelable