package jp.zyyx.favme.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import jp.zyyx.favme.R
import jp.zyyx.favme.connectmanager.NetworkConnectivityChecker
import jp.zyyx.favme.extension.popBackStack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment(), CoroutineScope {

    var snackbar: Snackbar? = null

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    private var _binding: VB? = null
    val binding: VB get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null) {
            throw IllegalArgumentException("Binding cannot be null")
        }
        setInternetConnectivityObserver()
        return binding.root
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB



    override fun onDestroyView() {
        coroutineContext.cancelChildren()
        super.onDestroyView()
        _binding = null
    }

    fun dialogWarningBack(onOk: () -> Unit) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.apply {
            setTitle(R.string.exit_screen)
            setMessage(R.string.do_you_exit_screen)
            setCancelable(false)
            setPositiveButton(getString(R.string.all_ok)) { _, _ ->
                onOk.invoke()
            }
            setNegativeButton(getString(R.string.all_cancel)) { _, _ -> }
        }
        alertDialog.show()
    }

    private fun setInternetConnectivityObserver() {
        NetworkConnectivityChecker.observe(this, liveDataObserver)
    }

    private val liveDataObserver: Observer<Boolean> = Observer { isConnected ->
        if (!isConnected) {
            //Can use your own logic later -- Using snackbar as default. Build your own listener to create custom view
            view?.let {
                snackbar = Snackbar.make(it, "No Internet Connection", Snackbar.LENGTH_LONG)
                snackbar?.show()
            }
        } else {
            snackbar?.dismiss()
        }
    }

}