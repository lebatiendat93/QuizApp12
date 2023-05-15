package jp.zyyx.favme.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import jp.zyyx.favme.MainActivity
import jp.zyyx.favme.MainFragment
import jp.zyyx.favme.R
import jp.zyyx.favme.base.BaseFragment
import jp.zyyx.favme.data.local.MySharePreference
import jp.zyyx.favme.databinding.FragmentForgotPassBinding
import jp.zyyx.favme.databinding.FragmentPolicyAndRulesBinding
import jp.zyyx.favme.extension.*
import jp.zyyx.favme.model.RemoteDataApi
import jp.zyyx.favme.model.Resource
import jp.zyyx.favme.model.ViewModelFactory
import jp.zyyx.favme.navigation.ScreenType

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

