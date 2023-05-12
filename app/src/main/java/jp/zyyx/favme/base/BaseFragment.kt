package jp.zyyx.favme.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import jp.zyyx.favme.extension.popBackStack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<B : ViewBinding> : Fragment(), CoroutineScope {


    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        return binding.root
    }
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            requireActivity().popBackStack()
        }
    }

    override fun onDestroyView() {
        coroutineContext.cancelChildren()
        super.onDestroyView()
    }

}