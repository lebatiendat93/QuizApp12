package jp.zyyx.favme.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.databinding.FragmentAccountBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.auth.LoginFragment
import kotlin.coroutines.CoroutineContext

class AccountFragment : BaseFragment<FragmentAccountBinding>(
    FragmentAccountBinding::inflate
) {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAccountBinding.inflate(layoutInflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {
        binding.btLogout.setOnClickListener {
            MySharePreference.getInstance().setAccessToken("")
            if (!MySharePreference.getInstance().isLogin()) {
                requireActivity().replaceFragment(
                    LoginFragment(),
                    R.id.fragment_container,
                    ScreenType.AuthFlow.Login.name
                )
            }
        }
    }

}