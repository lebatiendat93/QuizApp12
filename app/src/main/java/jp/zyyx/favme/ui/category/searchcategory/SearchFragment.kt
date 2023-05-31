package jp.zyyx.favme.ui.category.searchcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.data.remote.responses.home.ResultGetDepartment
import jp.zyyx.favme.databinding.FragmentSearchBinding
import jp.zyyx.favme.extension.longToast
import jp.zyyx.favme.extension.popBackStack
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory
import jp.zyyx.favme.ui.category.CategoryDepartmentAdapter
import jp.zyyx.favme.ui.home.HomeViewModel
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {

    private lateinit var searchSubjectAdapter: SearchSubjectAdapter
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory.create() }
    private var listData  = listOf<ResultGetDepartment>()

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

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }

    private fun filterList(text: String?) {
        if (text != null) {
            var filteredList = ArrayList<ResultGetDepartment>()
            for (i in listData ) {
                if (i.title.lowercase(Locale.ROOT).contains(text)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                requireActivity().longToast("No Data found")
            } else {
                searchSubjectAdapter.differ.submitList(filteredList)
            }

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
                            listData = it.data.result
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
