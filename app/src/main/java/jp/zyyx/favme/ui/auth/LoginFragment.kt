package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import jp.zyyx.favme.MainFragment
import jp.zyyx.favme.R
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentLoginBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.model.api.Resource
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java
    private var savePass = ""
    private var emailOrPhone = ""
    private var passWord = ""
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteData.buildAPI(AuthApi::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleObservable()

    }
    private fun initView() {
        handleShowOrHidePass()

        binding.edEmailOrPhone.doOnTextChanged { _, _, _, _ ->
            emailOrPhone = binding.edEmailOrPhone.text.toString().trim()
            checkEnableBtLogin()
        }

        binding.edPass.doOnTextChanged { _, _, _, _ ->
            passWord = binding.edPass.text.toString().trim()
            checkEnableBtLogin()
        }


        binding.btLogin.setOnClickListener {
            emailOrPhone = binding.edEmailOrPhone.text.toString().trim()
            passWord = binding.edPass.text.toString().trim()
            savePass = passWord
            viewModel.login(emailOrPhone, passWord)
        }

        binding.tvSignupNow.setOnClickListener {
            requireActivity().replaceFragment(
                SignInFragment(),
                R.id.fragment_container,
                ScreenType.AuthFlow.SignIn.name
            )
        }

        binding.cbSavePass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                MySharePreference.setSavePass(savePass)
                MySharePreference.isCbSavePass()
            } else {
                MySharePreference.setSavePass("")
                !MySharePreference.isCbSavePass()
            }

        }
    }

    private fun handleObservable() {
//        viewModel.loginResult.observe(viewLifecycleOwner, Observer { login ->
//            Log.e("LOGIN", login.result!!.access_token)
//            Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_LONG).show()
//            (activity as MainActivity).replaceFragment(
//                MainFragment(),
//                R.id.fragment_container
//            )
//        })



        viewModel.login.observe(viewLifecycleOwner) { login ->
            when (login) {
                is Resource.Success -> {
                    Log.e("LOGIN", login.value.toString())
                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_LONG).show()
                    MySharePreference.getInstance().setAccessToken(login.value.result.access_token)
                    requireActivity().replaceFragment(
                        MainFragment(),
                        R.id.fragment_container,
                        ScreenType.AuthFlow.MainFragment.name
                    )
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Login Error ${login.errorBody}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Resource.Loading -> {

                }
            }

        }
    }

    private fun handleShowOrHidePass() {
        binding.imgEyesShowOrHidePass.setOnClickListener {
            if (binding.edPass.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())) {
                binding.edPass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imgEyesShowOrHidePass.setImageResource(R.drawable.eyes_visibility)
            } else {
                binding.edPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.imgEyesShowOrHidePass.setImageResource(R.drawable.eyes_visibility_off)
            }
        }
    }

    private fun checkEnableBtLogin() {
        binding.btLogin.isEnabled = !(emailOrPhone.isEmpty() || passWord.isEmpty())
    }
}

