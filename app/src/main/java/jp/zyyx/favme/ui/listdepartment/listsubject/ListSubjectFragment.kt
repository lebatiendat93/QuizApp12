package jp.zyyx.favme.ui.listdepartment.listsubject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
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

class ListSubjectFragment : BaseFragment<FragmentListSubjectBinding>(
    FragmentListSubjectBinding::inflate
) {
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory.create() }
    private lateinit var listExamAdapter: ListExamAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListSubjectBinding.inflate(layoutInflater, container, false)

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().popBackStack()
        }
    }

    companion object {
        const val ID = "ID"
        fun newInstance(subjectId: Int) = ListSubjectFragment().apply {
            val bundle = Bundle()
            bundle.putInt(ID, subjectId)
            arguments = bundle
        }
    }

    private val subjectId: Int by lazy {
        requireArguments().getInt(ID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        handleObservable()
    }

    private fun initView() {

        val userId = MySharePreference.getInstance().getUserId()
        val header = MySharePreference.getInstance().getAccessToken()

        viewModel.getListExam(header, userId, subjectId, 3, 2, "desc")

        binding.tvBack.setOnClickListener {
            onBackPressedCallback.handleOnBackPressed()
        }

        binding.rcvItemSuggest.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            listExamAdapter = ListExamAdapter()
            adapter = listExamAdapter
            addItemDecoration(LinearSpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen._16dp)))
        }

        listExamAdapter.onItemClickListener = {

        }

        binding.imgSort.setOnClickListener {
            popupMenuSort()
        }


    }

    private fun handleObservable() {
        viewModel.getListExam.observe(viewLifecycleOwner) {
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
                            listExamAdapter.differ.submitList(it.data.result.list_exam)

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


    private fun popupMenuSort() {
        val popupMenu = PopupMenu(requireContext(), binding.imgSort)
        popupMenu.menuInflater.inflate(R.menu.menu_sort, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.sort_by_time -> {

                    true
                }
                R.id.sort_by_number_create -> {

                    true
                }
                R.id.sort_by_subject -> {

                    true

                }
                else -> false
            }
        }
        popupMenu.show()
    }


}