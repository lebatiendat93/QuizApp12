package jp.zyyx.favme.ui.home.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import jp.zyyx.favme.MainFragment
import jp.zyyx.favme.R
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.databinding.FragmentSystemBinding
import jp.zyyx.favme.extension.LinearSpacingItemDecoration
import jp.zyyx.favme.extension.gone
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.extension.visible
import jp.zyyx.favme.model.RemoteDataApiNew
import jp.zyyx.favme.model.ResourceNew
import jp.zyyx.favme.model.ViewModelFactoryNew
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.home.HomeViewModel

class SystemFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels { ViewModelFactoryNew.create() }

    private var _binding: FragmentSystemBinding? = null
    private val binding get() = _binding!!
    private lateinit var getDepartmentAdapter: GetDepartmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSystemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


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
                is ResourceNew.Loading -> {
//                    binding.pgLoading.visible()
                }
                is ResourceNew.Error -> {
//                    binding.pgLoading.gone()
                }
                is ResourceNew.Success -> {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}