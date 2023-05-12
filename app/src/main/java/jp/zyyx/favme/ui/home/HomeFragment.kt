package jp.zyyx.favme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.databinding.FragmentHomeBinding
import jp.zyyx.favme.extension.LinearSpacingItemDecoration
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory.create() }
    private lateinit var getDepartmentAdapter: GetDepartmentAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container , false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleObservable()

    }

    private fun initView() {

        binding.rcvDepartmentList.apply {
            layoutManager = LinearLayoutManager(context)
            getDepartmentAdapter = GetDepartmentAdapter()
            adapter = getDepartmentAdapter
            addItemDecoration(LinearSpacingItemDecoration(20))
        }
        val userId = MySharePreference.getInstance().getUserId()
        val header = MySharePreference.getInstance().getAccessToken()
        viewModel.getDepartment(header,userId,"Khoa")

        getDepartmentAdapter.onItemClickListener = {

        }


    }
    private fun handleObservable() {
        viewModel.getDepartment.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
//                    binding.pgLoading.visible()
                }
                is Resource.Error -> {
//                    binding.pgLoading.gone()
                }
                is Resource.Success -> {
                    when (it.data.statusCode) {
                        200 -> {
                            getDepartmentAdapter.differ.submitList(it.data.result)
                        }
                        400 -> {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG)
                                .show()
                        }
                        401 -> {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG)
                                .show()
                        }
                        500 -> {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                }
                else -> {}
            }
        }

    }
}