package jp.zyyx.favme.data.remote.responsedata

data class User(
    val access_token : String,
    val id: Int,
    val status : Int?= null,
    val name: String? = null,

)