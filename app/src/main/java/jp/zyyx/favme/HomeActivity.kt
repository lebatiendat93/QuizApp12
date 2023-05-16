package jp.zyyx.favme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import jp.zyyx.favme.databinding.ActivityMainBinding
import jp.zyyx.favme.extension.gone
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.extension.visible
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.splash.SplashFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val onBackStackChangedListener = FragmentManager.OnBackStackChangedListener {
        if (supportFragmentManager.backStackEntryCount >= 1) {
            binding.fragmentContainer.visible()
        } else {
            binding.fragmentContainer.gone()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        replaceFragment(SplashFragment(), R.id.fragment_container, ScreenType.SplashFlow.Splash.name)

    }


}