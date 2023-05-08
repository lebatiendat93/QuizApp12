package jp.zyyx.favme.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.data.remote.responses.LoginResponses
import jp.zyyx.favme.data.remote.responses.RegisterResponses
import jp.zyyx.favme.model.ModelException
import jp.zyyx.favme.model.ResourceNew
import jp.zyyx.favme.repository.AuthRepositoryNew
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AuthViewModelNew(
    private val repository: AuthRepositoryNew
) : ViewModel() {

    private val _login: MutableLiveData<ResourceNew<LoginResponses>> = MutableLiveData()
    val login: LiveData<ResourceNew<LoginResponses>>
        get() = _login

    private val _register: MutableLiveData<ResourceNew<RegisterResponses>> = MutableLiveData()
    val register: LiveData<ResourceNew<RegisterResponses>>
        get() = _register


    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        val loginRequest = LoginRequest(email, password)
        repository.login(loginRequest).onStart { ResourceNew.Loading }
            .catch { error ->
                _login.value = ResourceNew.Error(error as ModelException)
            }
            .collect {
                _login.value = ResourceNew.Success(it)
            }
    }


    fun register(
        email: String,
        name: String,
        phone: String,
        birthday: String,
        password: String,
    ) = viewModelScope.launch {
        val registerRequest = RegisterRequest(email, name, phone, birthday, password)
        repository.register(registerRequest).onStart {
            ResourceNew.Loading
        }.catch { error ->
            _register.value = ResourceNew.Error(error as ModelException)
        }.collect {
            _register.value = ResourceNew.Success(it)
        }
    }




}


