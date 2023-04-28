package jp.zyyx.favme.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.zyyx.favme.R
import jp.zyyx.favme.databinding.FragmentLoginBinding
import jp.zyyx.favme.databinding.FragmentLoginOrRegisterBinding
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.ui.base.BaseFragment

class LoginOrRegisterFragment : BaseFragment<AuthViewModel, FragmentLoginOrRegisterBinding, AuthRepository>() {



    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginOrRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteData.buildAPI(AuthApi::class.java))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    private fun initView() {

        binding.btLogin.setOnClickListener {
        }

        binding.btSignUp.setOnClickListener {

        }

    }

    private fun handleObservable() {

    }


}