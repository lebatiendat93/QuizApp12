package jp.zyyx.favme.ui.home.system.departmentlist

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
import jp.zyyx.favme.databinding.FragmentDepartmentListBinding
import jp.zyyx.favme.databinding.FragmentHomeBinding
import jp.zyyx.favme.databinding.FragmentMainBinding
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.ui.account.AccountFragment
import jp.zyyx.favme.ui.analysis.AnalysisFragment
import jp.zyyx.favme.ui.auth.AuthViewModel
import jp.zyyx.favme.ui.home.system.SystemFragment
import jp.zyyx.favme.ui.home.user.UserFragment
import jp.zyyx.favme.ui.input.InputFragment

class DepartmentListFragment : Fragment() {

    private var _binding: FragmentDepartmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepartmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}