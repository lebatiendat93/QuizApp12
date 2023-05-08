package jp.zyyx.favme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.zyyx.favme.data.remote.requestparam.ListDepartmentInfoRequest
import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.data.remote.responses.GetDepartmentResponses
import jp.zyyx.favme.data.remote.responses.ListDepartmentInfoResponses
import jp.zyyx.favme.data.remote.responses.LoginResponses
import jp.zyyx.favme.data.remote.responses.RegisterResponses
import jp.zyyx.favme.model.ModelException
import jp.zyyx.favme.model.ResourceNew
import jp.zyyx.favme.repository.AuthRepositoryNew
import jp.zyyx.favme.repository.HomeRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _getDepartment: MutableLiveData<ResourceNew<GetDepartmentResponses>> =
        MutableLiveData()
    val getDepartment: LiveData<ResourceNew<GetDepartmentResponses>>
        get() = _getDepartment

    private val _listDepartmentInfo: MutableLiveData<ResourceNew<ListDepartmentInfoResponses>> =
        MutableLiveData()
    val listDepartmentInfo: LiveData<ResourceNew<ListDepartmentInfoResponses>>
        get() = _listDepartmentInfo

    fun getDepartment(
        header: String,
        userId: Int,
        keyword: String,
    ) = viewModelScope.launch {
        repository.getDepartmentList(header, userId, keyword).onStart { ResourceNew.Loading }
            .catch { error ->
                _getDepartment.value = ResourceNew.Error(error as ModelException)
            }
            .collect {
                _getDepartment.value = ResourceNew.Success(it)
            }
    }

    fun listDepartmentInfo(
        header: String,
        userId: Int
    ) = viewModelScope.launch {
        repository.listDepartmentInfo(header, userId).onStart { ResourceNew.Loading }
            .catch { error ->
                _listDepartmentInfo.value = ResourceNew.Error(error as ModelException)
            }
            .collect {
                _listDepartmentInfo.value = ResourceNew.Success(it)
            }
    }


}


