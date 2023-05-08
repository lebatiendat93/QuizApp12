package jp.zyyx.favme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentAnalysisBinding
import jp.zyyx.favme.databinding.FragmentHomeBinding
import jp.zyyx.favme.databinding.FragmentMainBinding
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.ui.account.AccountFragment
import jp.zyyx.favme.ui.analysis.AnalysisFragment
import jp.zyyx.favme.ui.auth.AuthViewModel
import jp.zyyx.favme.ui.home.system.SystemFragment
import jp.zyyx.favme.ui.home.user.UserFragment
import jp.zyyx.favme.ui.input.InputFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        val fragment = mutableListOf(
            SystemFragment(),
            UserFragment(),
            )
        val adapter = ViewPager2HomeAdapter(
            fragment, this
        )
        binding.viewPager2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text =  getString(R.string.system)
                1 -> tab.text =  getString(R.string.user)
                else -> {
                    getString(R.string.system)
                }
            }
        }.attach()

//
//        binding.tabLayout.addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if (tab != null) {
//                    binding.viewPager2.currentItem = tab.position
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//
//            }
//        })
//
//        binding.viewPager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
//            }
//        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}