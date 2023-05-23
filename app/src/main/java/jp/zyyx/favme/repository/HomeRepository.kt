package jp.zyyx.favme.repository

import jp.zyyx.favme.base.Repository
import jp.zyyx.favme.data.remote.requestparam.home.*
import jp.zyyx.favme.data.remote.responses.home.*
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
        keyword: String
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
                val responses = api.getListDepartmentInfo(header, listDepartmentInfoRequest)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getListExam(
        header: String,
        userId: Int,
        subject_id: Int,
        type: Int,
        sort_field: Int,
        sort_by: String
    ): Flow<ExamResponses> {
        return flow {
            try {
                val examRequest = ExamRequest(userId, subject_id, type, sort_field, sort_by)
                val responses = api.getListExam(header, examRequest)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getExamDetail(
        header: String,
        userId: Int,
        exam_id: Int,
    ): Flow<ExamDetailResponses> {
        return flow {
            try {
                val examDetailRequest = ExamDetailRequest(userId, exam_id)
                val responses = api.getExamDetail(header, examDetailRequest)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchSubject(
        header: String,
        id: Int,
        departmentId: Int,
        keyWord: String,
    ): Flow<SearchSubjectResponses> {
        return flow {
            try {
                val searchSubjectRequest = SearchSubjectRequest(id, departmentId,keyWord)
                val responses = api.searchSubject(header, searchSubjectRequest)
                emit(responses)
            } catch (e: Exception) {
                e.printStackTrace()
                e.catchCommonErrors()
            }
        }.flowOn(Dispatchers.IO)
    }


}