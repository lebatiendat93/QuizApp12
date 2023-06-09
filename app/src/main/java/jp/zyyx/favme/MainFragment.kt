package jp.zyyx.favme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentMainBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.account.AccountFragment
import jp.zyyx.favme.ui.analysis.AnalysisFragment
import jp.zyyx.favme.ui.home.HomeFragment
import jp.zyyx.favme.ui.input.InputFragment
import jp.zyyx.favme.ui.category.CategoryFragment

class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentMainBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {

        handleBottomNavigation()

        binding.fabButtonSearch.setOnClickListener {
            requireActivity().replaceFragment(
                CategoryFragment(),
                R.id.fragment_container,
                ScreenType.HomeFlow.CategoryFragment.name
            )
        }

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