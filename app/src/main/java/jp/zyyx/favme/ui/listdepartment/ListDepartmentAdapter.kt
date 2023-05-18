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
    var onClickListenerSeeAll: ((item: ResultListDepartmentInfo) -> Unit)? = null

    var onItemClickListenerSubject: ((item: ResultListDepartmentInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_department, parent, false)
        return ListSubjectViewHolder(view)
    }

    override fun getItemCount()  = listDepartment.size


    override fun onBindViewHolder(holder: ListSubjectViewHolder, position: Int) {
        val data = listDepartment[position]

        holder.seeAll.setOnClickListener {
            onClickListenerSeeAll?.invoke(data)
        }

        holder.itemView.setOnClickListener {
            onItemClickListenerSubject?.invoke(data)
        }
        holder.departmentName.text = data.title

        holder.rcvSubject.setHasFixedSize(false)
        holder.rcvSubject.layoutManager = LinearLayoutManager(holder.departmentName.context)
        holder.rcvSubject.addItemDecoration(LinearSpacingItemDecoration(holder.itemView.resources.getDimensionPixelOffset(R.dimen._16dp)))
        val listSubjectAdapter = ListSubjectAdapter()
        holder.rcvSubject.adapter = listSubjectAdapter

        (holder.rcvSubject.adapter as ListSubjectAdapter).setData(data.subjects)
        (holder.rcvSubject.adapter as ListSubjectAdapter).resultListDepartmentInfo = data

        listSubjectAdapter.onItemClickListenerSubject = onItemClickListenerSubject

    }

    inner class ListSubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val departmentName: AppCompatTextView = itemView.findViewById(R.id.tv_department_name)
        val seeAll: AppCompatTextView = itemView.findViewById(R.id.tv_see_all)
        val rcvSubject: RecyclerView = itemView.findViewById(R.id.rcv_subject)
    }

}