package jp.zyyx.favme.repository

import jp.zyyx.favme.base.Repository
import jp.zyyx.favme.data.remote.requestparam.auth.ForgotPassRequest
import jp.zyyx.favme.data.remote.requestparam.auth.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.auth.RegisterRequest
import jp.zyyx.favme.data.remote.responses.auth.ForgotPassResponse
import jp.zyyx.favme.data.remote.responses.auth.LoginResponses
import jp.zyyx.favme.data.remote.responses.auth.RegisterResponses
import jp.zyyx.favme.model.RemoteDataApi
import jp.zyyx.favme.model.catchCommonErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepository(
    private val api: RemoteDataApi
) : Repository() {

    suspend fun login(
        loginParam: LoginRequest
    ): Flow<LoginResponses> {
        return flow {
            try {
                val responses = api.login(loginParam)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun register(
        registerRequest: RegisterRequest
    ): Flow<RegisterResponses> {
        return flow {
            try {
                val responses = api.register(registerRequest)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun forgotPass(
        forgotPassRequest: ForgotPassRequest
    ): Flow<ForgotPassResponse> {
        return flow {
            try {
                val responses = api.forgotPassword(forgotPassRequest)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }

}