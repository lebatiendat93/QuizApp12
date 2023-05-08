package jp.zyyx.favme.model

import jp.zyyx.favme.model.api.Logger
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException


open class ModelException(message: String?) : Exception(message)

class UnhandledDataException(message: String? = "Lỗi không xác định") : ModelException(message)

class UnexpectedDataException(message: String? = "Lỗi kết quả không mong đợi") : ModelException(message)

class ConnectionException(message: String? = "Lỗi kết nối") : ModelException(message)

class BadRequestException(message: String? = "Lỗi truy vấn") : ModelException(message)

class UnauthorisedException(message: String?) : ModelException(message)

class NotFoundException(message: String? = "Không tìm thấy") : ModelException(message)

class PermissionException(message: String? = "Lỗi quyền") : ModelException(message)

class UnprocessableEntityException(message: String?) : ModelException(message)

fun Exception.catchCommonErrors() {
    Logger.printDebug("==============this $this")
    when (this) {
        is SocketTimeoutException -> {
            throw ConnectionException(message)
        }
        is HttpException -> {
            val data = this.response()?.errorBody()?.string()
            val jObjError = JSONObject(data)
            val path = jObjError.getString("message")
            when (this.code()) {
                401 -> throw UnauthorisedException(path)
                404 -> throw NotFoundException(path)
                422 -> throw UnprocessableEntityException(path)
                403 -> throw PermissionException(path)
            }
        }
        else -> throw UnhandledDataException(this.message)
    }
}