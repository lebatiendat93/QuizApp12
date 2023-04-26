package jp.zyyx.favme.ui.splash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import jp.zyyx.favme.R

class SliderImageSplashAdapter(private val imageList: ArrayList<Int> ,
private val viewPager2 : ViewPager2) : RecyclerView.Adapter<SliderImageSplashAdapter.SliderViewHolder>() {


    class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView =  view.rootView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_slider_splash, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.imageView.setBackgroundResource(imageList[position])
    }

    override fun getItemCount() = imageList.size


}