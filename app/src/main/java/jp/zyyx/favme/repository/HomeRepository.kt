package jp.zyyx.favme.repository

import jp.zyyx.favme.base.Repository
import jp.zyyx.favme.data.remote.requestparam.GetDepartmentRequest
import jp.zyyx.favme.data.remote.requestparam.ListDepartmentInfoRequest
import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.data.remote.responses.GetDepartmentResponses
import jp.zyyx.favme.data.remote.responses.ListDepartmentInfoResponses
import jp.zyyx.favme.data.remote.responses.LoginResponses
import jp.zyyx.favme.data.remote.responses.RegisterResponses
import jp.zyyx.favme.model.RemoteDataApiNew
import jp.zyyx.favme.model.catchCommonErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Header

class HomeRepository(
    private val api: RemoteDataApiNew
) : Repository() {

    suspend fun getDepartmentList(
        header: String,
        userId: Int,
        keyword : String
    ): Flow<GetDepartmentResponses> {
        return flow {
            try {
                val getDepartmentRequest = GetDepartmentRequest(userId, keyword)
                val responses = api.getDepartmentList(header, getDepartmentRequest)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun listDepartmentInfo(
        header: String,
        userId: Int
    ): Flow<ListDepartmentInfoResponses> {
        return flow {
            try {
                val listDepartmentInfoRequest = ListDepartmentInfoRequest(userId)
                val responses = api.listDepartmentInfo(header, listDepartmentInfoRequest)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }



}