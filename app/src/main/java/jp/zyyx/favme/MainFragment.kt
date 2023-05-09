package jp.zyyx.favme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import jp.zyyx.favme.databinding.FragmentMainBinding
import jp.zyyx.favme.ui.account.AccountFragment
import jp.zyyx.favme.ui.analysis.AnalysisFragment
import jp.zyyx.favme.ui.home.HomeFragment
import jp.zyyx.favme.ui.input.InputFragment

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
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