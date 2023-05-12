package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import jp.zyyx.favme.MainFragment
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.databinding.FragmentLoginBinding
import jp.zyyx.favme.extension.*
import jp.zyyx.favme.model.RemoteDataApi
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory
import jp.zyyx.favme.navigation.ScreenType

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: AuthViewModel by viewModels { ViewModelFactory.create() }

    private var savePass = ""
    private var emailOrPhone = ""
    private var passWord = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleObservable()
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed(
                ) {
                    if (emailOrPhone.isNotEmpty() || passWord.isNotEmpty()) {
                        requireActivity().popBackStack()
                    } else {
                        dialogWarningBack {
                            requireActivity().popBackStack()
                        }
                    }
                }
            })
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


        binding.btLogin.setOnClickPreventingDouble {
            binding.pgLoading.visible()
            emailOrPhone = binding.edEmailOrPhone.text.toString().trim()
            passWord = binding.edPass.text.toString().trim()
            savePass = passWord
            viewModel.login(emailOrPhone, passWord)
        }

        binding.tvSignupNow.setOnClickPreventingDouble {
            requireActivity().replaceFragment(
                RegisterFragment(),
                R.id.fragment_container,
                ScreenType.AuthFlow.Register.name
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
        viewModel.login.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.pgLoading.visible()
                }
                is Resource.Error -> {
                    binding.pgLoading.gone()
                }
                is Resource.Success -> {
                    binding.pgLoading.gone()
                    when (it.data.statusCode) {
                        200 -> {
                            RemoteDataApi.applyAccessToken(it.data.result.access_token)
                            MySharePreference.getInstance()
                                .setAccessToken(it.data.result.access_token)
                            MySharePreference.getInstance().setUserId(it.data.result.user_id)
                            Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_LONG)
                                .show()

                            requireActivity().replaceFragment(
                                MainFragment(),
                                R.id.fragment_container,
                                ScreenType.AuthFlow.MainFragment.name
                            )
                        }

                        400 -> {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG)
                                .show()
                        }

                        401 -> {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG)
                                .show()
                        }

                        500 -> {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                }
                else -> {
                    binding.pgLoading.gone()
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

