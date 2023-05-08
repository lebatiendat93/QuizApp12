package jp.zyyx.favme.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.zyyx.favme.base.BaseRepositoryNew
import jp.zyyx.favme.repository.AuthRepositoryNew
import jp.zyyx.favme.repository.HomeRepository
import jp.zyyx.favme.ui.auth.AuthViewModelNew
import jp.zyyx.favme.ui.home.HomeViewModel

class ViewModelFactoryNew(
    private val repository: BaseRepositoryNew
): ViewModelProvider.Factory {

    companion object {
        fun create(): ViewModelFactoryNew {
            return ViewModelFactoryNew(
                BaseRepositoryNew(
                    RemoteDataApiNew.instance
                )
            )
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return when {
            modelClass.isAssignableFrom(AuthViewModelNew::class.java) ->
                AuthViewModelNew(repository.create(AuthRepositoryNew::class.java)) as T

           modelClass.isAssignableFrom(HomeViewModel::class.java) ->
               HomeViewModel(repository.create(HomeRepository::class.java)) as T
            else -> throw ClassNotFoundException()
        }
    }
}