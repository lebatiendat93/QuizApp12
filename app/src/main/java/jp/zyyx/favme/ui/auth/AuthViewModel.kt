package jp.zyyx.favme.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.zyyx.favme.data.remote.requestparam.LoginRequest
import jp.zyyx.favme.data.remote.requestparam.RegisterRequest
import jp.zyyx.favme.data.remote.responses.LoginResponses
import jp.zyyx.favme.data.remote.responses.RegisterResponses
import jp.zyyx.favme.model.api.Resource
import jp.zyyx.favme.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {
    //
    private val _login: MutableLiveData<Resource<LoginResponses>> = MutableLiveData()
    val login: LiveData<Resource<LoginResponses>>
        get() = _login

    private val _register: MutableLiveData<Resource<RegisterResponses>> = MutableLiveData()
    val register: LiveData<Resource<RegisterResponses>>
        get() = _register

//    private val _loginResult = MutableLiveData<LoginResponsesModel>()
//    val loginResult: LiveData<LoginResponsesModel> = _loginResult


    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        val startTime = System.currentTimeMillis()
        val loginRequest = LoginRequest(email, password)
        _login.postValue(repository.login(loginRequest))

        val time = System.currentTimeMillis() - startTime
        Log.e("TIME", time.toString())
        Log.e("TIME1", Thread.currentThread().name)

    }

    fun register(
        email: String,
        name: String,
        phone: String,
        birthday: String,
        password: String,
    ) = viewModelScope.launch {
        val registerRequest = RegisterRequest(email, name, phone, birthday, password)
        _register.postValue(repository.register(registerRequest))
    }

}


//    fun login(
//        email: String,
//        password: String
//    ) {
//        val loginRequest = LoginRequest(email, password)
//        NewRemoteAPI.retrofit.login(loginRequest).enqueue(object : Callback<LoginResponsesModel> {
//            override fun onResponse(
//                call: Call<LoginResponsesModel>,
//                response: Response<LoginResponsesModel>
//            ) {
//                if (response.isSuccessful) {
//                    _loginResult.postValue(response.body())
//
//                    Log.e("LOGIN API CALL", response.body().toString())
//                } else {
//                    // Xử lý khi gọi API bị lỗi
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponsesModel>, t: Throwable) {
//                // Xử lý khi có lỗi trong quá trình gọi API
//            }
//        })
//
//    }