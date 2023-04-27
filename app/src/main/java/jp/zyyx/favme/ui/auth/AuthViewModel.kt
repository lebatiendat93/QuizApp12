package jp.zyyx.favme.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.zyyx.favme.model.api.Resource
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.responses.LoginResponses
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _login: MutableLiveData<Resource<LoginResponses>> = MutableLiveData()
    val login: LiveData<Resource<LoginResponses>>
        get() = _login

     fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
         Log.e("DATA", "${_login.value}")
        _login.value = repository.login(email, password)
    }




}