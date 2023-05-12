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
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.repository.AuthRepositoryNew
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepositoryNew
) : ViewModel() {

    private val _login: MutableLiveData<Resource<LoginResponses>> = MutableLiveData()
    val login: LiveData<Resource<LoginResponses>>
        get() = _login

    private val _register: MutableLiveData<Resource<RegisterResponses>> = MutableLiveData()
    val register: LiveData<Resource<RegisterResponses>>
        get() = _register


    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        val loginRequest = LoginRequest(email, password)
        repository.login(loginRequest).onStart { Resource.Loading }
            .catch { error ->
                _login.value = Resource.Error(error as ModelException)
            }
            .collect {
                _login.value = Resource.Success(it)
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
            Resource.Loading
        }.catch { error ->
            _register.value = Resource.Error(error as ModelException)
        }.collect {
            _register.value = Resource.Success(it)
        }
    }




}


