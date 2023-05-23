package jp.zyyx.favme.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentIntroduceBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.auth.LoginOrRegisterFragment

class IntroduceFragment : BaseFragment<FragmentIntroduceBinding>(
    FragmentIntroduceBinding::inflate
) {


    private lateinit var imageList: ArrayList<Int>
    private lateinit var sliderAdapter: SliderImageSplashAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentIntroduceBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        imageList = ArrayList()
        imageList.add(R.drawable.picture_introduce_1)
        imageList.add(R.drawable.picture_introduce_2)
        imageList.add(R.drawable.picture_introduce_3)

        sliderAdapter = SliderImageSplashAdapter(imageList, binding.viewPager2)
        binding.viewPager2.adapter = sliderAdapter

        binding.viewPager2.apply {
            clipChildren = false
            clipToPadding = false
        }
        binding.indicator.setViewPager(binding.viewPager2)

        binding.btArrowRight.setOnClickListener {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
            binding.indicator.setViewPager(binding.viewPager2)
            when(binding.viewPager2.currentItem) {
                0 -> binding.tvGtDescription1.text = getString(R.string.gt_description_1)
                1 -> binding.tvGtDescription1.text = getString(R.string.gt_description_2)
                2 -> {
                    binding.tvGtDescription1.text = getString(R.string.gt_description_3)
                    binding.btArrowRight.setOnClickListener {
                        requireActivity().replaceFragment(LoginOrRegisterFragment(), R.id.fragment_container, ScreenType.AuthFlow.LoginOrRegister.name)
                    }
                }

            }
        }
    }
}