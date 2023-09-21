package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentForgotPassBinding
import jp.zyyx.favme.extension.gone
import jp.zyyx.favme.extension.longToast
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.extension.visible
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory
import jp.zyyx.favme.navigation.ScreenType

class ForgotPassFragment : BaseFragment<FragmentForgotPassBinding>(FragmentForgotPassBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels { ViewModelFactory.create() }

    private var emailOrPhone = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentForgotPassBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initView()
//        handleObservable()

    }

    private fun initView() {
        binding.edEmailOrPhone.doOnTextChanged { _, _, _, _ ->
            emailOrPhone = binding.edEmailOrPhone.text.toString().trim()
            checkEnableBtLogin()
        }

        binding.btSend.setOnClickListener {
            viewModel.forgotPass(emailOrPhone)
            binding.pgLoading.visible()
        }


    }

    private fun handleObservable() {
        viewModel.forgotPass.observe(viewLifecycleOwner) {
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
                            requireContext().longToast("Change Password Success")
                            requireActivity().replaceFragment(
                                LoginFragment(),
                                R.id.fragment_container,
                                ScreenType.AuthFlow.Login.name
                            )
                        }
                        400 -> {
                            requireContext().longToast(it.data.message.toString())
                        }

                        401 -> {
                            requireContext().longToast(it.data.message.toString())
                        }

                        500 -> {
                            requireContext().longToast(it.data.message.toString())
                        }
                    }

                }
                else -> {
                    binding.pgLoading.gone()
                }

            }

        }
    }

    private fun checkEnableBtLogin() {
        binding.btSend.isEnabled = emailOrPhone.isNotEmpty()
    }
}

