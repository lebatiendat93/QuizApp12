package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.zyyx.favme.MainActivity
import jp.zyyx.favme.databinding.FragmentCreateAccountBinding
import jp.zyyx.favme.ui.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentLoginBinding
import jp.zyyx.favme.repository.AuthRepository

class SignInFragment : BaseFragment<AuthViewModel, FragmentCreateAccountBinding, AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreateAccountBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteData.buildAPI(AuthApi::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initView() {

        binding.btRegister.setOnClickListener {

        }


    }

    private fun handleObservable() {

    }


}