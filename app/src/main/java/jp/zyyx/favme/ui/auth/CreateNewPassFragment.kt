package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentCreateNewPassBinding
import jp.zyyx.favme.extension.gone
import jp.zyyx.favme.extension.longToast
import jp.zyyx.favme.extension.popBackStack
import jp.zyyx.favme.extension.visible
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory

class CreateNewPassFragment : BaseFragment<FragmentCreateNewPassBinding>(FragmentCreateNewPassBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels { ViewModelFactory.create() }

    private var emailOrPhone = ""
    private var passWord = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreateNewPassBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleObservable()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (emailOrPhone.isNotEmpty() || passWord.isNotEmpty()) {
                        dialogWarningBack {
                            requireActivity().popBackStack()
                        }
                    } else {
                        requireActivity().popBackStack()
                    }
                }
            })
    }

    private fun initView() {
        handleShowOrHidePass()
    }

    private fun handleObservable() {
//        viewModel.login.observe(viewLifecycleOwner) {
//            when (it) {
//                is Resource.Loading -> {
//                    binding.pgLoading.visible()
//                }
//                is Resource.Error -> {
//                    binding.pgLoading.gone()
//                }
//                is Resource.Success -> {
//                    binding.pgLoading.gone()
//                    when (it.data.statusCode) {
//                        200 -> {
//
//                        }
//                        400 -> {
//                            requireContext().longToast(it.data.message.toString())
//                        }
//
//                        401 -> {
//                            requireContext().longToast(it.data.message.toString())
//                        }
//
//                        500 -> {
//                            requireContext().longToast(it.data.message.toString())
//                        }
//                    }
//
//                }
//                else -> {
//                    binding.pgLoading.gone()
//                }
//
//            }
//
//        }
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

        binding.imgEyesShowOrHidePassConfirm.setOnClickListener {
            if (binding.edConfirmPass.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())) {
                binding.edConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imgEyesShowOrHidePassConfirm.setImageResource(R.drawable.eyes_visibility)
            } else {
                binding.edConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.imgEyesShowOrHidePassConfirm.setImageResource(R.drawable.eyes_visibility_off)
            }
        }
    }

    private fun checkEnableBtRepass() {
        binding.btLogin.isEnabled = !(emailOrPhone.isEmpty() || passWord.isEmpty())
    }
}

