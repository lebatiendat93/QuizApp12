//package jp.zyyx.favme.data.remote.responses
//
//
//data class LoginResponsesModel(
//    val statusCode: Int,
//    val result: NewResult?,
//    val message: String?= null
//)
//
//data class NewResult(
//    val access_token: String,
//    val user_id: Int,
//    val status: Int? = null ,
//    val name: String? = null,
//)
//
//
//
//sealed class LoginResult {
//    data class Success(val token: String, val userId: Int) : LoginResult()
//    data class Error(val message: String) : LoginResult()
//}
//
//// Tạo extension function để chuyển đổi LoginResponse thành LoginResult
//fun LoginResponsesModel.toLoginResult(): LoginResult {
//    return if (this.statusCode == 200 && this.result != null) {
//        LoginResult.Success(this.result.access_token, this.result.user_id)
//    } else {
//        LoginResult.Error("Login failed")
//    }
//}