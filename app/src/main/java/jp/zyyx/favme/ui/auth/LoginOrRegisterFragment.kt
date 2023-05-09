package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.zyyx.favme.R
import jp.zyyx.favme.databinding.FragmentLoginOrRegisterBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.extension.setOnClickPreventingDouble
import jp.zyyx.favme.navigation.ScreenType

class LoginOrRegisterFragment : BaseFragment<AuthViewModel, FragmentLoginOrRegisterBinding, AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginOrRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteData.buildAPI(AuthApi::class.java))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }


    private fun initView() {

        binding.btLogin.setOnClickPreventingDouble {
            requireActivity().replaceFragment(LoginFragmentNew(), R.id.fragment_container, ScreenType.AuthFlow.Login.name)
        }

        binding.btSignUp.setOnClickPreventingDouble {
            requireActivity().replaceFragment(RegisterFragment(), R.id.fragment_container, ScreenType.AuthFlow.Register.name)
        }

    }


}