package jp.zyyx.favme.ui.home

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
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
import jp.zyyx.favme.data.remote.responses.home.GetDepartmentResponses
import jp.zyyx.favme.data.remote.responses.home.ResultGetDepartment
import jp.zyyx.favme.databinding.FragmentHomeBinding
import jp.zyyx.favme.extension.LinearSpacingItemDecoration
import jp.zyyx.favme.extension.popBackStack
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.auth.AuthViewModel
import jp.zyyx.favme.ui.listdepartment.ListDepartmentFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory.create() }
    private val viewModelAuth: AuthViewModel by viewModels { ViewModelFactory.create() }
    private lateinit var getDepartmentAdapter: GetDepartmentAdapter
    private var resultGetDepartment: List<ResultGetDepartment> = mutableListOf()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().popBackStack()
                }
            })

        initView()
        handleObservable()

    }

    private fun initView() {
        binding.rcvDepartmentList.apply {
            layoutManager = LinearLayoutManager(context)
            getDepartmentAdapter = GetDepartmentAdapter().apply {
                setHasStableIds(true)
                setHasFixedSize(false)
            }
            adapter = getDepartmentAdapter
            addItemDecoration(LinearSpacingItemDecoration(20))
        }

        val userId = MySharePreference.getInstance().getUserId()
        val header = MySharePreference.getInstance().getAccessToken()
        viewModel.getDepartment(header, userId, "Khoa")

        binding.tvSystem.setOnClickListener {
//            handleText()
            val listDepartmentSystem =
                viewModel.listDepartmentResponse.value?.result?.filter { it.is_exam_by_system }
            getDepartmentAdapter.differ.submitList(listDepartmentSystem)
        }

        binding.tvUser.setOnClickListener {
//            handleText()

            val listDepartmentUser = viewModel.listDepartmentResponse.value.let { it?.result }
            getDepartmentAdapter.differ.submitList(listDepartmentUser)
        }

        getDepartmentAdapter.onItemClickListener = {


        }

        binding.tvSeeAll.setOnClickListener {
            requireActivity().replaceFragment(
                ListDepartmentFragment(),
                R.id.fragment_container_home,
                ScreenType.HomeFlow.ListDepartmentFragment.name
            )
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
                            val listDepartmentSystem =
                                it.data.result.filter { it.is_exam_by_system }
                            getDepartmentAdapter.differ.submitList(listDepartmentSystem)
                            viewModel.setDepartmentResponse(it.data)
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

    private fun handleText() {
        val spannableStringSystem = SpannableString(binding.tvSystem.text)
        val spannableStringUser = SpannableString(binding.tvUser.text)
        binding.tvSystem.setTextIsSelectable(true)
        spannableStringSystem.setSpan(UnderlineSpan(), 0, spannableStringSystem.length, 0)
        binding.tvSystem.setTextColor(requireContext().getColor(R.color.black))

        if (binding.tvUser.isSelected) {
            binding.tvUser.setTextIsSelectable(true)


        } else {

        }

    }

//    {
//        spannableStringSystem.setSpan(UnderlineSpan(), 0 , spannableStringSystem.length,0 )
//        binding.tvSystem.setTextColor(requireContext().getColor(R.color.black))
//    } else {
//        spannableStringSystem.setSpan(UnderlineSpan(), 0 , 0,0 )
//        binding.tvSystem.setTextColor(requireContext().getColor(R.color.color_gray3))
//    }

}