package jp.zyyx.favme.ui.listdepartment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.databinding.FragmentListSubjectBinding
import jp.zyyx.favme.extension.LinearSpacingItemDecoration
import jp.zyyx.favme.extension.popBackStack
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory
import jp.zyyx.favme.ui.home.HomeViewModel

class ListDepartmentFragment : BaseFragment<FragmentListSubjectBinding>(
    FragmentListSubjectBinding::inflate
) {
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory.create() }
    private lateinit var listDepartmentAdapter: ListDepartmentAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListSubjectBinding.inflate(layoutInflater, container, false)

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        handleObservable()
    }

    private fun initView() {

        binding.tvBack.setOnClickListener {
            onBackPressedCallback.handleOnBackPressed()
        }

        binding.rcvItemSuggest.apply {
            layoutManager = LinearLayoutManager(context)
            listDepartmentAdapter = ListDepartmentAdapter()
            adapter = listDepartmentAdapter
            addItemDecoration(LinearSpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen._16dp)))
        }

        listDepartmentAdapter.onItemClickListener = {

        }

        val userId = MySharePreference.getInstance().getUserId()
        val header = MySharePreference.getInstance().getAccessToken()
        viewModel.listDepartmentInfo(header, userId)

    }

    private fun handleObservable() {
        viewModel.listDepartmentInfo.observe(viewLifecycleOwner) {
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
                            listDepartmentAdapter.setData(it.data.result)
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