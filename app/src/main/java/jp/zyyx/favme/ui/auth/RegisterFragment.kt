package jp.zyyx.favme.ui.auth

import android.app.DatePickerDialog
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
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentCreateAccountBinding
import jp.zyyx.favme.extension.*
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory
import jp.zyyx.favme.navigation.ScreenType
import java.text.SimpleDateFormat
import java.util.*


class RegisterFragment : BaseFragment<FragmentCreateAccountBinding>() {

    private val viewModel: AuthViewModel by viewModels { ViewModelFactory.create() }

    private var userName = ""
    private var email = ""
    private var phone = ""
    private var birthday = ""
    private var pass = ""
    private var confirmPass = ""


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleObservable()
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed(
                ) {
                    if (!binding.btRegister.isEnabled) {
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

        binding.tvLoginNow.setOnClickListener {
            requireActivity().replaceFragment(
                LoginFragment(),
                R.id.fragment_container,
                ScreenType.AuthFlow.Login.name
            )
        }

        binding.tvEnterBirthday.setOnClickPreventingDouble {
            chooseBirthday()
        }

        binding.edEnterEmail.doOnTextChanged { _, _, _, _ ->
            email = binding.edEnterEmail.text.toString().trim()
            checkEnableBtSignup()
            if (validEmail()) {
                binding.tvNoticeEnterEmail.visible()
            } else {
                binding.tvNoticeEnterEmail.gone()
            }
        }

        binding.edEnterPhone.doOnTextChanged { _, _, _, _ ->
            phone = binding.edEnterPhone.text.toString().trim()
            checkEnableBtSignup()
            if (validPhoneNumber()) {
                binding.tvNoticeEnterPhone.visible()
            } else {
                binding.tvNoticeEnterPhone.gone()
            }
        }

        binding.edPass.doOnTextChanged { _, _, _, _ ->
            pass = binding.edPass.text.toString().trim()
            checkEnableBtSignup()
            if (validPassword()) {
                binding.tvNoticeEnterPass.visible()
            } else {
                binding.tvNoticeEnterPass.gone()
            }
        }

        binding.edEnterThePass.doOnTextChanged { _, _, _, _ ->
            confirmPass = binding.edEnterThePass.text.toString().trim()
            checkEnableBtSignup()
            if (validEnterPassword()) {
                binding.tvNoticeEnterConfirmPass.visible()
            } else {
                binding.tvNoticeEnterConfirmPass.gone()
            }
        }

        binding.edEnterFullName.doOnTextChanged { _, _, _, _ ->
            userName = binding.edEnterFullName.text.toString().trim()
            checkEnableBtSignup()
        }

        binding.btRegister.setOnClickListener {
            binding.pgLoading.visible()

            email = binding.edEnterEmail.text.toString().trim()
            phone = binding.edEnterPhone.text.toString().trim()
            userName = binding.edEnterFullName.text.toString().trim()
            birthday = binding.tvEnterBirthday.text.toString().trim()
            pass = binding.edPass.text.toString().trim()
            confirmPass = binding.edEnterThePass.text.toString().trim()

            binding.tvNoticeEnterPass.gone()
            binding.tvNoticeEnterPhone.gone()
            binding.tvNoticeEnterEmail.gone()
            binding.tvNoticeEnterName.gone()
            binding.tvNoticeEnterBirthDay.gone()
            binding.tvNoticeEnterConfirmPass.gone()

            handleLogicRegister(email, phone, userName, birthday, pass, confirmPass)
        }


    }


    private fun handleObservable() {
        viewModel.register.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.pgLoading.gone()
                    when (it.data.statusCode) {
                        200 -> {
                            requireContext().longToast("Register Success")
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
                is Resource.Error -> {
                    binding.pgLoading.gone()
                    Toast.makeText(requireContext(), "Failed Register", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    binding.pgLoading.gone()
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

        binding.imgEyesShowOrHidePassAgain.setOnClickListener {
            if (binding.edEnterThePass.transformationMethod.equals(HideReturnsTransformationMethod.getInstance())) {
                binding.edEnterThePass.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                binding.imgEyesShowOrHidePassAgain.setImageResource(R.drawable.eyes_visibility)
            } else {
                binding.edEnterThePass.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.imgEyesShowOrHidePassAgain.setImageResource(R.drawable.eyes_visibility_off)
            }
        }
    }

    private fun chooseBirthday() {
        val calendar = Calendar.getInstance()
        val datePickerDialog =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateBirthDay(calendar)
            }
        binding.tvEnterBirthday.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePickerDialog,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateBirthDay(calendar: Calendar) {
        val date = SimpleDateFormat("dd").format(calendar.time)
        val month = SimpleDateFormat("MM").format(calendar.time)
        val year = SimpleDateFormat("YYYY").format(calendar.time)
        binding.tvEnterBirthday.text = "$year-$month-$date"

    }

    private fun checkEnableBtSignup() {
        binding.btRegister.isEnabled =
            !(userName.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty() || confirmPass.isEmpty())
    }

    private fun handleLogicRegister(
        email: String,
        phone: String,
        name: String,
        birthday: String,
        pass: String,
        enterThePass: String
    ) {
        binding.pgLoading.gone()
        if (email.isEmpty() || phone.isEmpty() || name.isEmpty() || birthday.isEmpty() || pass.isEmpty() || enterThePass.isEmpty()) {
            requireContext().longToast(getString(R.string.notice_request_input_full_information))
            return
        } else {
            if (validEmail()) {
                requireContext().longToast(getString(R.string.notice_request_enter_correct_format_email))
                binding.tvNoticeEnterEmail.visible()
                return
            } else {
                if (validPhoneNumber()) {
                    requireContext().longToast(getString(R.string.notice_please_enter_phone_number))
                    binding.tvNoticeEnterPhone.visible()
                    return
                } else {
                    if (birthday == getString(R.string.dk_22_birthday_hint)) {
                        requireContext().longToast(getString(R.string.notice_please_enter_birthday))
                        binding.tvNoticeEnterBirthDay.visible()
                        return
                    } else {
                        if (validPassword()) {
                            requireContext().longToast(getString(R.string.notice_pass_must_than_6_word))
                            binding.tvNoticeEnterPass.visible()
                            return
                        } else {
                            if (validEnterPassword()) {
                                requireContext().longToast(getString(R.string.notice_pass_and_confirm_pass_do_not_same))
                                binding.tvNoticeEnterConfirmPass.visible()
                                return
                            } else {
                                viewModel.register(email, name, phone, birthday, pass)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validPassword(): Boolean {
        return (!pass.contains("[A-Z]".toRegex()) || pass.length < 7)
    }

    private fun validEnterPassword(): Boolean {
        return (confirmPass != pass)
    }

    private fun validPhoneNumber(): Boolean {
        return (phone.length != 10)
    }

    private fun validEmail(): Boolean {
        val emailPattern = "[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return (!email.contains(emailPattern))
    }

}