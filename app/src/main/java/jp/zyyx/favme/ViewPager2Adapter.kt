package jp.zyyx.favme

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import jp.zyyx.favme.ui.account.AccountFragment
import jp.zyyx.favme.ui.analysis.AnalysisFragment
import jp.zyyx.favme.ui.home.HomeFragment
import jp.zyyx.favme.ui.input.InputFragment

class ViewPager2Adapter(private val fragments: List<Fragment>, fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> InputFragment()
            2 -> AnalysisFragment()
            3 -> AccountFragment()
            else -> HomeFragment()
        }
    }
}