package jp.zyyx.favme.responses

data class User(
    val access_token : String,
    val id: Int,
    val status : Int?= null,
    val name: String? = null,

)