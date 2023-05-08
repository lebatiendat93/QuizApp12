package jp.zyyx.favme.repository

import jp.zyyx.favme.base.Repository
import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.data.remote.responses.LoginResponses
import jp.zyyx.favme.data.remote.responses.RegisterResponses
import jp.zyyx.favme.model.RemoteDataApiNew
import jp.zyyx.favme.model.catchCommonErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepositoryNew(
    private val api: RemoteDataApiNew
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

}