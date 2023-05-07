package jp.zyyx.favme.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import jp.zyyx.favme.model.RemoteDataAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<VM : ViewModel, B : ViewBinding, R : BaseRepository> : Fragment(), CoroutineScope {


    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    protected lateinit var binding: B
    protected lateinit var viewModel : VM
    protected  val remoteData = RemoteDataAPI()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this,factory)[getViewModel()]
        return binding.root
    }


    abstract fun getViewModel() : Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    abstract fun getFragmentRepository() : R


    override fun onDestroyView() {
        coroutineContext.cancelChildren()
        super.onDestroyView()
    }

}