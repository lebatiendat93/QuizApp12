package jp.zyyx.favme.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import jp.zyyx.favme.ui.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentLoginBinding
import jp.zyyx.favme.repository.AuthRepository

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteData.buildAPI(AuthApi::class.java))
}