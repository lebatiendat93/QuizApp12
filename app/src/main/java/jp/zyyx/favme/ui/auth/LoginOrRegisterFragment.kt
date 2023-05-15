package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentLoginOrRegisterBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.extension.setOnClickPreventingDouble
import jp.zyyx.favme.navigation.ScreenType

class LoginOrRegisterFragment : BaseFragment<FragmentLoginOrRegisterBinding>(
    FragmentLoginOrRegisterBinding::inflate
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginOrRegisterBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed(
                ) {

                }
            })
    }

    private fun initView() {

        binding.btLogin.setOnClickPreventingDouble {
            requireActivity().replaceFragment(
                LoginFragment(),
                R.id.fragment_container,
                ScreenType.AuthFlow.Login.name
            )
        }

        binding.btSignUp.setOnClickPreventingDouble {
            requireActivity().replaceFragment(
                RegisterFragment(),
                R.id.fragment_container,
                ScreenType.AuthFlow.Register.name
            )
        }

    }

    fun onBackClick(): Boolean {
        //This method handle onBackPressed()! return true or false
        return false
    }


}