package jp.zyyx.favme.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.databinding.FragmentSearchBinding
import jp.zyyx.favme.extension.GridSpacingItemDecoration
import jp.zyyx.favme.extension.popBackStack
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory
import jp.zyyx.favme.ui.home.HomeViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {

    private lateinit var searchDepartmentAdapter: SearchDepartmentAdapter
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory.create() }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(layoutInflater, container, false)

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
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            searchDepartmentAdapter = SearchDepartmentAdapter()
            adapter = searchDepartmentAdapter
            addItemDecoration(GridSpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen._12dp)))
        }

        val userId = MySharePreference.getInstance().getUserId()
        val header = MySharePreference.getInstance().getAccessToken()
        viewModel.getDepartment(header, userId, "Khoa")

        searchDepartmentAdapter.differ.submitList(viewModel.listDepartmentResponse.value?.result)
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
                            searchDepartmentAdapter.differ.submitList(it.data.result)
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