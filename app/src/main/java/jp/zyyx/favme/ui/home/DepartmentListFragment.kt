package jp.zyyx.favme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentDepartmentListBinding


class DepartmentListFragment : BaseFragment<FragmentDepartmentListBinding>() {



    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDepartmentListBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {


    }


}