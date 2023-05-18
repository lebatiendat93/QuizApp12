package jp.zyyx.favme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.zyyx.favme.data.remote.responses.home.*
import jp.zyyx.favme.model.ModelException
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.repository.HomeRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _listDepartmentResponse = MutableLiveData<GetDepartmentResponses>()
    val listDepartmentResponse: LiveData<GetDepartmentResponses> = _listDepartmentResponse

    private val _getDepartment: MutableLiveData<Resource<GetDepartmentResponses>> =
        MutableLiveData()
    val getDepartment: LiveData<Resource<GetDepartmentResponses>>
        get() = _getDepartment

    private val _listDepartmentInfo: MutableLiveData<Resource<ListDepartmentInfoResponses>> =
        MutableLiveData()
    val listDepartmentInfo: LiveData<Resource<ListDepartmentInfoResponses>>
        get() = _listDepartmentInfo

    private val _getListExam: MutableLiveData<Resource<ExamResponses>> =
        MutableLiveData()
    val getListExam: LiveData<Resource<ExamResponses>>
        get() = _getListExam


    private val _getExamDetail: MutableLiveData<Resource<ExamDetailResponses>> =
        MutableLiveData()
    val getExamDetail: LiveData<Resource<ExamDetailResponses>>
        get() = _getExamDetail

    fun setDepartmentResponse(list: GetDepartmentResponses) {
        _listDepartmentResponse.value = list
    }

    fun getDepartment(
        header: String,
        userId: Int,
        keyword: String,
    ) = viewModelScope.launch {
        repository.getDepartmentList(header, userId, keyword).onStart { Resource.Loading }
            .catch { error ->
                _getDepartment.value = Resource.Error(error as ModelException)
            }
            .collect {
                _getDepartment.value = Resource.Success(it)
            }
    }

    fun listDepartmentInfo(
        header: String,
        userId: Int
    ) = viewModelScope.launch {
        repository.listDepartmentInfo(header, userId).onStart { Resource.Loading }
            .catch { error ->
                _listDepartmentInfo.value = Resource.Error(error as ModelException)
            }
            .collect {
                _listDepartmentInfo.value = Resource.Success(it)
            }
    }

    fun getListExam(
        header: String,
        userId: Int,
        subject_id: Int,
        type: Int,
        sort_field: Int,
        sort_by: String
    ) = viewModelScope.launch {
        repository.getListExam(header, userId, subject_id, type, sort_field, sort_by)
            .onStart { Resource.Loading }
            .catch { error ->
                _getListExam.value = Resource.Error(error as ModelException)
            }
            .collect {
                _getListExam.value = Resource.Success(it)
            }
    }

    fun getExamDetail(
        header: String,
        userId: Int,
        exam_id: Int,
    ) = viewModelScope.launch {
        repository.getExamDetail(header, userId, exam_id)
            .onStart { Resource.Loading }
            .catch { error ->
                _getExamDetail.value = Resource.Error(error as ModelException)
            }
            .collect {
                _getExamDetail.value = Resource.Success(it)
            }
    }

}


