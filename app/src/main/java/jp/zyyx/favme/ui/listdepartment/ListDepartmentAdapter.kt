package jp.zyyx.favme.ui.listdepartment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.zyyx.favme.R
import jp.zyyx.favme.data.remote.responses.home.ResultListDepartmentInfo
import jp.zyyx.favme.extension.LinearSpacingItemDecoration

class ListDepartmentAdapter :
    RecyclerView.Adapter<ListDepartmentAdapter.ListSubjectViewHolder>() {

    private var listDepartment = listOf<ResultListDepartmentInfo>()

    fun setData(list: List<ResultListDepartmentInfo>) {
        this.listDepartment = list
        notifyDataSetChanged()
    }
    var onItemClickListener: ((item: ResultListDepartmentInfo) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_department, parent, false)
        return ListSubjectViewHolder(view)
    }

    override fun getItemCount()  = listDepartment.size


    override fun onBindViewHolder(holder: ListSubjectViewHolder, position: Int) {
        val data = listDepartment[position]

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }
        holder.departmentName.text = data.title

        holder.rcvSubject.setHasFixedSize(true)
        holder.rcvSubject.layoutManager = LinearLayoutManager(holder.departmentName.context)
        holder.rcvSubject.addItemDecoration(LinearSpacingItemDecoration(holder.itemView.resources.getDimensionPixelOffset(R.dimen._16dp)))
        val adapter = ListSubjectAdapter(data.subjects)
        holder.rcvSubject.adapter = adapter
    }

    inner class ListSubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val departmentName: AppCompatTextView = itemView.findViewById(R.id.tv_department_name)
        val seeAll: AppCompatTextView = itemView.findViewById(R.id.tv_see_all)
        val rcvSubject: RecyclerView = itemView.findViewById(R.id.rcv_subject)
    }

}