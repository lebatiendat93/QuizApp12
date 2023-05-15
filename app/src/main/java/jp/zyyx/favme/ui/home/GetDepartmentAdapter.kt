package jp.zyyx.favme.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.zyyx.favme.data.remote.responses.home.ResultGetDepartment
import jp.zyyx.favme.databinding.ItemFacultyBinding

class GetDepartmentAdapter : RecyclerView.Adapter<GetDepartmentAdapter.GetDepartmentViewHolder>() {

    private lateinit var binding: ItemFacultyBinding

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetDepartmentViewHolder {
        binding = ItemFacultyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                tvFacultyName.text = item.title
                tvSubjectName.text = item.subject_list
            }
            Glide.with(binding.imgFaculty.context).load(item.image).into(binding.imgFaculty)
        }
    }

}