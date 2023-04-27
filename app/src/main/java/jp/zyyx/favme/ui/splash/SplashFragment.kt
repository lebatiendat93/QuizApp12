package jp.zyyx.favme.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.zyyx.favme.MainActivity
import jp.zyyx.favme.R
import jp.zyyx.favme.databinding.FragmentSplashBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.ui.splash.IntroduceFragment

class   SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        object : CountDownTimer(2000, 2000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                (activity as MainActivity).replaceFragment(
                    IntroduceFragment(), R.id.fragment_container
                )
            }
        }.start()

    }


}