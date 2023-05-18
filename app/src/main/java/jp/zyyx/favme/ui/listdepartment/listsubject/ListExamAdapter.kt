package jp.zyyx.favme.ui.listdepartment.listsubject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.zyyx.favme.data.remote.responses.home.ExamResult
import jp.zyyx.favme.data.remote.responses.home.ListExam
import jp.zyyx.favme.data.remote.responses.home.ResultGetDepartment
import jp.zyyx.favme.databinding.ItemFacultyBinding

class ListExamAdapter : RecyclerView.Adapter<ListExamAdapter.ListExamViewHolder>() {

    private lateinit var binding: ItemFacultyBinding

    var onItemClickListener: ((item: ListExam) -> Unit)? = null

    var sortListBy = 1

    private val differCallBack = object : DiffUtil.ItemCallback<ListExam>() {
        override fun areItemsTheSame(
            oldItem: ListExam,
            newItem: ListExam
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ListExam,
            newItem: ListExam
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListExamViewHolder {
        binding = ItemFacultyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListExamViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ListExamViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.setData(data)
        holder.setIsRecyclable(false)
    }

    inner class ListExamViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: ListExam) {
            binding.tvFacultyName.text = item.title
            Glide.with(binding.tvSubjectName.context).load(item.image).into(binding.imgFaculty)

            when (sortListBy) {
                1 -> binding.tvSubjectName.text = item.time.toString()
                2 -> binding.tvSubjectName.text = item.number.toString()
                3 -> binding.tvSubjectName.text = item.saved_num.toString()
            }

        }
    }

}