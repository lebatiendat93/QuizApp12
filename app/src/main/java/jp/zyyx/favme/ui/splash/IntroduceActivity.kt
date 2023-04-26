package jp.zyyx.favme.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import jp.zyyx.favme.R
import jp.zyyx.favme.databinding.ActivityIntroduceBinding

class IntroduceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroduceBinding
    private lateinit var imageList: ArrayList<Int>
    private lateinit var sliderAdapter: SliderImageSplashAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        binding.viewPager2.isUserInputEnabled = false
        binding.indicator.setViewPager(binding.viewPager2)

        binding.btArrowRight.setOnClickListener {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1
            binding.indicator.setViewPager(binding.viewPager2)
            when(binding.viewPager2.currentItem) {
                0 -> binding.tvGtDescription1.text = getString(R.string.gt_description_1)
                1 -> binding.tvGtDescription1.text = getString(R.string.gt_description_2)
                2 -> binding.tvGtDescription1.text = getString(R.string.gt_description_3)
            }
        }

        if (binding.viewPager2.currentItem == 2) {
            binding.btArrowRight.setOnClickListener {

            }
        }

    }
}