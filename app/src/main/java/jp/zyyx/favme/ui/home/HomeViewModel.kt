package jp.zyyx.favme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.zyyx.favme.data.remote.responses.home.GetDepartmentResponses
import jp.zyyx.favme.data.remote.responses.home.ListDepartmentInfoResponses
import jp.zyyx.favme.model.ModelException
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.repository.HomeRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _getDepartment: MutableLiveData<Resource<GetDepartmentResponses>> =
        MutableLiveData()
    val getDepartment: LiveData<Resource<GetDepartmentResponses>>
        get() = _getDepartment

    private val _listDepartmentInfo: MutableLiveData<Resource<ListDepartmentInfoResponses>> =
        MutableLiveData()
    val listDepartmentInfo: LiveData<Resource<ListDepartmentInfoResponses>>
        get() = _listDepartmentInfo

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


}


