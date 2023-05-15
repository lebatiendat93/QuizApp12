package jp.zyyx.favme.repository

import jp.zyyx.favme.base.Repository
import jp.zyyx.favme.data.remote.requestparam.home.GetDepartmentRequest
import jp.zyyx.favme.data.remote.requestparam.home.ListDepartmentInfoRequest
import jp.zyyx.favme.data.remote.responses.home.GetDepartmentResponses
import jp.zyyx.favme.data.remote.responses.home.ListDepartmentInfoResponses
import jp.zyyx.favme.model.RemoteDataApi
import jp.zyyx.favme.model.catchCommonErrors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository(
    private val api: RemoteDataApi
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