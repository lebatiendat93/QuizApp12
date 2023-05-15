package jp.zyyx.favme.data.remote.requestparam.home

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class ListDepartmentInfoRequest(
    val user_id: Int,
) : Parcelable