package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import jp.zyyx.favme.ui.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentLoginBinding
import jp.zyyx.favme.databinding.FragmentMainBinding
import jp.zyyx.favme.model.api.Resource
import jp.zyyx.favme.repository.AuthRepository

class LoginFragment : BaseFragment<AuthViewModel, FragmentMainBinding, AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteData.buildAPI(AuthApi::class.java))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initView()
//        handleObservable()

    }

//    private fun initView() {
//
//        binding.btLogin.setOnClickListener {
//            val emailOrPhone = binding.edEmailOrPhone.text.toString().trim()
//            val passWord = binding.edPass.text.toString().trim()
//            viewModel.login(emailOrPhone,passWord)
//        }
//    }
//    private fun handleObservable() {
//        viewModel.login.observe(viewLifecycleOwner, Observer {
//            when(it) {
//                is Resource.Success -> {
//                    Toast.makeText(requireContext(),"Login Success", Toast.LENGTH_LONG).show()
//                }
//                is Resource.Failure -> {
//
//                    Toast.makeText(requireContext(),"Failed Login", Toast.LENGTH_LONG).show()
//
//                }
//            }
//        })
//    }
}