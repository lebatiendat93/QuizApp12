package jp.zyyx.favme.ui.category.searchcategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.zyyx.favme.R
import jp.zyyx.favme.data.remote.responses.home.ResultGetDepartment
import jp.zyyx.favme.databinding.ItemSubjectBinding
import jp.zyyx.favme.databinding.ItemSuggestBinding
import kotlin.random.Random

class SearchSubjectAdapter : RecyclerView.Adapter<SearchSubjectAdapter.GetDepartmentViewHolder>() {

    private lateinit var binding: ItemSubjectBinding

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
        binding = ItemSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
            binding.apply {
                tvSubjectName.text = item.title
                tvExamNumber.text = item.subject_list
            }
            Glide.with(binding.imgSubject.context).load(item.image).into(binding.imgSubject)
        }
    }


}