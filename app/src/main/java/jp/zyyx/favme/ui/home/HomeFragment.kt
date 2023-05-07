package jp.zyyx.favme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentAnalysisBinding
import jp.zyyx.favme.databinding.FragmentHomeBinding
import jp.zyyx.favme.databinding.FragmentMainBinding
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.ui.auth.AuthViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}