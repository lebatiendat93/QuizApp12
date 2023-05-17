package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.databinding.FragmentPolicyAndRulesBinding
import jp.zyyx.favme.extension.popBackStack

class PolicyAndRulesFragment :
    BaseFragment<FragmentPolicyAndRulesBinding>(FragmentPolicyAndRulesBinding::inflate) {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPolicyAndRulesBinding.inflate(layoutInflater, container, false)

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        binding.btComeBack.setOnClickListener {
            onBackPressedCallback.handleOnBackPressed()
        }

    }
}

