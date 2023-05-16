package jp.zyyx.favme.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.zyyx.favme.data.remote.requestparam.auth.ForgotPassRequest
import jp.zyyx.favme.data.remote.requestparam.auth.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.auth.RegisterRequest
import jp.zyyx.favme.data.remote.responses.auth.ForgotPassResponse
import jp.zyyx.favme.data.remote.responses.auth.LoginResponses
import jp.zyyx.favme.data.remote.responses.auth.RegisterResponses
import jp.zyyx.favme.model.ModelException
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.repository.AuthRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {


    var user : LoginResponses? = null


    private val _login: MutableLiveData<Resource<LoginResponses>> = MutableLiveData()
    val login: LiveData<Resource<LoginResponses>>
        get() = _login

    private val _register: MutableLiveData<Resource<RegisterResponses>> = MutableLiveData()
    val register: LiveData<Resource<RegisterResponses>>
        get() = _register

    private val _forgotPass: MutableLiveData<Resource<ForgotPassResponse>> = MutableLiveData()
    val forgotPass: LiveData<Resource<ForgotPassResponse>>
        get() = _forgotPass

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

    fun forgotPass(
        email: String
    ) = viewModelScope.launch {
        val forgotPassRequest = ForgotPassRequest(email)
        repository.forgotPass(forgotPassRequest).onStart {
            Resource.Loading
        }.catch { error ->
            _forgotPass.value = Resource.Error(error as ModelException)
        }.collect {
            _forgotPass.value = Resource.Success(it)
        }
    }



}


