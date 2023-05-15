package jp.zyyx.favme.data.remote.responses.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class ListDepartmentInfoResponses(
    val statusCode: Int,
    val result: List<ResultListDepartmentInfo>,
    val message: String? = null
) : Parcelable

@Parcelize
@Keep
data class ResultListDepartmentInfo(
    val id: Int,
    val title: String,
    val image: String,
    val subjects: List<Subjects>,
) : Parcelable


@Parcelize
@Keep
data class Subjects(
    val id: Int,
    val title: String,
    val image: String,
) : Parcelable