package jp.zyyx.favme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import jp.zyyx.favme.databinding.ActivityHomeBinding
import jp.zyyx.favme.databinding.ActivityMainBinding
import jp.zyyx.favme.extension.gone
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.extension.visible
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.account.AccountFragment
import jp.zyyx.favme.ui.analysis.AnalysisFragment
import jp.zyyx.favme.ui.home.HomeFragment
import jp.zyyx.favme.ui.input.InputFragment
import jp.zyyx.favme.ui.splash.SplashFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)

        handleBottomNavigation()

    }

    private fun handleBottomNavigation() {
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        val fragment = mutableListOf(
            HomeFragment(),
            InputFragment(),
            AnalysisFragment(),
            AccountFragment()
        )

        val viewPager2Adapter = ViewPager2Adapter(fragment, this)
        binding.viewPager2.adapter = viewPager2Adapter

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNavigationView.selectedItemId = R.id.home
                    1 -> binding.bottomNavigationView.selectedItemId = R.id.input
                    2 -> binding.bottomNavigationView.selectedItemId = R.id.analysis
                    3 -> binding.bottomNavigationView.selectedItemId = R.id.account
                }
            }
        })

        binding.bottomNavigationView.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.home -> binding.viewPager2.setCurrentItem(0, true)
                R.id.input -> binding.viewPager2.setCurrentItem(1, true)
                R.id.analysis -> binding.viewPager2.setCurrentItem(2, true)
                R.id.account -> binding.viewPager2.setCurrentItem(3, true)
            }
            true
        }
    }



}