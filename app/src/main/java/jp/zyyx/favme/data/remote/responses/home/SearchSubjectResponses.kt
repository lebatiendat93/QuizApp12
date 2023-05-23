package jp.zyyx.favme.data.remote.responses.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class SearchSubjectResponses(
    val statusCode: Int,
    val result: List<SearchSubjectResult> ,
    val message: String? = null
) : Parcelable

@Parcelize
@Keep
data class SearchSubjectResult(
    val id: Int,
    val title: String,
    val count_exam: Int,
    val description: String,
    val image: String,
) : Parcelable


