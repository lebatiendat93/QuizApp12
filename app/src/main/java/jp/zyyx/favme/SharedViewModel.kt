package jp.zyyx.favme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.zyyx.favme.data.remote.requestparam.auth.RegisterRequest
import jp.zyyx.favme.data.remote.responses.auth.ResultLogin

class SharedViewModel : ViewModel() {

    private var _loginData = MutableLiveData<ResultLogin>()
    val loginData: LiveData<ResultLogin> = _loginData

    private var _registerData = MutableLiveData<RegisterRequest>()
    val registerData: LiveData<RegisterRequest> = _registerData

    fun saveLoginData(resultLogin: ResultLogin) {
        _loginData.value = resultLogin
    }

    fun saveRegisterData(registerResponses: RegisterRequest) {
        _registerData.value = registerResponses
    }

}