package jp.zyyx.favme.ui.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentAnalysisBinding
import jp.zyyx.favme.databinding.FragmentHomeBinding
import jp.zyyx.favme.databinding.FragmentInputBinding
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.ui.auth.AuthViewModel

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(layoutInflater, container, false)
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