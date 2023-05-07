package jp.zyyx.favme.ui.auth

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentCreateAccountBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.extension.setOnClickPreventingDouble
import jp.zyyx.favme.extension.toast
import jp.zyyx.favme.extension.visible
import jp.zyyx.favme.model.api.Resource
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.repository.AuthRepository
import java.text.SimpleDateFormat
import java.util.*


class SignInFragment : BaseFragment<AuthViewModel, FragmentCreateAccountBinding, AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java

    private var name = ""
    private var email = ""
    private var phone = ""
    private var birthday = ""
    private var pass = ""
    private var enterThePass = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreateAccountBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteData.buildAPI(AuthApi::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleObservable()
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
        }

        binding.edEnterPhone.doOnTextChanged { _, _, _, _ ->
            phone = binding.edEnterPhone.text.toString().trim()
            checkEnableBtSignup()
        }

        binding.edPass.doOnTextChanged { _, _, _, _ ->
            pass = binding.edPass.text.toString().trim()
            checkEnableBtSignup()
        }

        binding.edEnterThePass.doOnTextChanged { _, _, _, _ ->
            enterThePass = binding.edEnterThePass.text.toString().trim()
            checkEnableBtSignup()
        }

        binding.edEnterFullName.doOnTextChanged { _, _, _, _ ->
            name = binding.edEnterFullName.text.toString().trim()
            checkEnableBtSignup()
        }

        binding.btRegister.setOnClickListener {
            email = binding.edEnterEmail.text.toString().trim()
            phone = binding.edEnterPhone.text.toString().trim()
            name = binding.edEnterFullName.text.toString().trim()
            birthday = binding.tvEnterBirthday.text.toString().trim()
            pass = binding.edPass.text.toString().trim()
            enterThePass = binding.edEnterThePass.text.toString().trim()

            binding.tvNoticeEnterPass.visible(false)
            binding.tvNoticeEnterPhone.visible(false)
            binding.tvNoticeEnterEmail.visible(false)
            binding.tvNoticeEnterName.visible(false)
            binding.tvNoticeEnterBirthDay.visible(false)
            binding.tvNoticeEnterConfirmPass.visible(false)

            handleLogicRegister(email, phone, name, birthday, pass, enterThePass)
        }


    }

    private fun handleObservable() {
        viewModel.register.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_LONG).show()
                    Log.e("Register", it.value.toString())
                    requireActivity().replaceFragment(
                        LoginFragment(),
                        R.id.fragment_container,
                        ScreenType.AuthFlow.Login.name
                    )
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Failed Register", Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {}
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
            !(name.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty() || enterThePass.isEmpty())
    }

    private fun handleLogicRegister(
        email: String,
        phone: String,
        name: String,
        birthday: String,
        pass: String,
        enterThePass: String
    ) {
        if (email.isEmpty() || phone.isEmpty() || name.isEmpty() || birthday.isEmpty() || pass.isEmpty() || enterThePass.isEmpty()) {
            requireContext().toast(getString(R.string.request_input_full_information))
            return
        } else {
            if (!email.contains("@gmail.com")) {
                requireContext().toast(getString(R.string.request_enter_correct_format_email))
                binding.tvNoticeEnterEmail.visible(true)
                return
            } else {
                if (phone.length != 10) {
                    requireContext().toast(getString(R.string.please_enter_phone_number))
                    binding.tvNoticeEnterPhone.visible(true)
                    return
                } else {
                    if (birthday == getString(R.string.enter_birth_day)) {
                        requireContext().toast(getString(R.string.please_enter_birthday))
                        binding.tvNoticeEnterBirthDay.visible(true)
                        return
                    } else {
                        if (pass.length < 7) {
                            requireContext().toast(getString(R.string.pass_must_than_6_word))
                            binding.tvNoticeEnterPass.visible(true)
                            return
                        } else {
                            if (pass != enterThePass) {
                                requireContext().toast(getString(R.string.pass_and_confirm_pass_do_not_same))
                                binding.tvNoticeEnterConfirmPass.visible(true)
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

}