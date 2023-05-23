package jp.zyyx.favme.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.zyyx.favme.MainFragment
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.databinding.FragmentSplashBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.auth.LoginFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>(
    FragmentSplashBinding::inflate
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentSplashBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        object : CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (!MySharePreference.getInstance().getFirstStartApp()) {
                    requireActivity().replaceFragment(
                        IntroduceFragment(), R.id.fragment_container,
                        ScreenType.SplashFlow.Introduce.name
                    )
                    MySharePreference.getInstance().setFirstStartApp(true)
                } else {
                    if (MySharePreference.isLogin()) {
                        requireActivity().replaceFragment(
                            MainFragment(), R.id.fragment_container,
                            ScreenType.HomeFlow.MainFragment.name
                        )

                    } else {
                        requireActivity().replaceFragment(
                            LoginFragment(), R.id.fragment_container,
                            ScreenType.AuthFlow.Login.name
                        )
                    }
                }
            }
        }.start()

    }

}