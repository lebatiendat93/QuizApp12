package jp.zyyx.favme.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.zyyx.favme.R
import jp.zyyx.favme.data.remote.responses.home.ResultGetDepartment
import jp.zyyx.favme.databinding.ItemSuggestBinding
import kotlin.random.Random

class CategoryDepartmentAdapter : RecyclerView.Adapter<CategoryDepartmentAdapter.GetDepartmentViewHolder>() {

    private lateinit var binding: ItemSuggestBinding

    var onItemClickListener: ((item: ResultGetDepartment) -> Unit)? = null

    private val differCallBack = object : DiffUtil.ItemCallback<ResultGetDepartment>() {
        override fun areItemsTheSame(
            oldItem: ResultGetDepartment,
            newItem: ResultGetDepartment
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResultGetDepartment,
            newItem: ResultGetDepartment
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun getItemId(position: Int): Long {
        return super.getItemId(position).hashCode().toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetDepartmentViewHolder {
        binding = ItemSuggestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GetDepartmentViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: GetDepartmentViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.setData(data)
        holder.setIsRecyclable(false)
    }

    inner class GetDepartmentViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ResultGetDepartment) {
            binding.cadView.setCardBackgroundColor(binding.imgFaculty.resources.getColor(getRandomColor(), null))
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
            binding.apply {
                tvFacultyName.text = item.title
                tvSubjectName.text = item.subject_list
            }
            Glide.with(binding.imgFaculty.context).load(item.image).into(binding.imgFaculty)
        }
    }

    private fun getRandomColor(): Int {
        val color = ArrayList<Int> ()
        color.add(R.color.color_red)
        color.add(R.color.color_green)
        color.add(R.color.color_primary)
        color.add(R.color.color_pink)
        color.add(R.color.color_gray3)
        color.add(R.color.color_purple)
        color.add(R.color.color_accent2)
        val number = Random.nextInt(color.size)
        return color[number]
    }

}