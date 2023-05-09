package jp.zyyx.favme.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2HomeAdapter(private val fragments : List<Fragment>, fragment : Fragment):
    FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
      return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }



}