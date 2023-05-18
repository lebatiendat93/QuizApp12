package jp.zyyx.favme.ui.listdepartment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.zyyx.favme.R
import jp.zyyx.favme.data.remote.responses.home.ResultListDepartmentInfo
import jp.zyyx.favme.data.remote.responses.home.Subjects

class ListSubjectAdapter() :
    RecyclerView.Adapter<ListSubjectAdapter.ListSubjectViewHolder>() {

    private var listSubject =  listOf<Subjects>()
    var onItemClickListenerSubject: ((subjects: ResultListDepartmentInfo) -> Unit)? = null

    lateinit var resultListDepartmentInfo : ResultListDepartmentInfo
    fun setData(list :  List<Subjects>) {
        this.listSubject = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return ListSubjectViewHolder(view)
    }

    override fun getItemCount() = listSubject.size


    override fun onBindViewHolder(holder: ListSubjectViewHolder, position: Int) {
        val data = listSubject[position]
        holder.subjectName.text = data.title
        Glide.with(holder.examNumber.context).load(data.image).into(holder.imgSubject)
        holder.itemView.setOnClickListener {
            onItemClickListenerSubject?.invoke(resultListDepartmentInfo)
        }
    }

    inner class ListSubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val subjectName: AppCompatTextView = itemView.findViewById(R.id.tv_subject_name)
        val examNumber: AppCompatTextView = itemView.findViewById(R.id.tv_exam_number)
        val imgSubject: ImageView = itemView.findViewById(R.id.img_subject)

    }

}