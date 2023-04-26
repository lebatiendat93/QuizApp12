package jp.zyyx.favme.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.zyyx.favme.R
import jp.zyyx.favme.databinding.FragmentLoginBinding
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.ui.base.BaseFragment

class LoginOrRegisterFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {
    override fun getViewModel(): Class<AuthViewModel> {
        TODO("Not yet implemented")
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        TODO("Not yet implemented")
    }

    override fun getFragmentRepository(): AuthRepository {
        TODO("Not yet implemented")
    }


}