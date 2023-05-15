package jp.zyyx.favme.data.remote.responses.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class GetDepartmentResponses(
    val statusCode: Int,
    val result: List<ResultGetDepartment>,
    val message: String? = null
) : Parcelable

@Parcelize
@Keep
data class ResultGetDepartment(
    val description: String,
    val id: Int,
    val image: String,
    val is_exam_by_system: Boolean,
    val is_exam_by_user: Boolean,
    val subject_list: String,
    val title: String,
) : Parcelable