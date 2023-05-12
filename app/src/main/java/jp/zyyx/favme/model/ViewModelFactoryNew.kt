package jp.zyyx.favme.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.zyyx.favme.base.BaseRepository
import jp.zyyx.favme.repository.AuthRepositoryNew
import jp.zyyx.favme.repository.HomeRepository
import jp.zyyx.favme.ui.auth.AuthViewModel
import jp.zyyx.favme.ui.home.HomeViewModel

class ViewModelFactoryNew(
    private val repository: BaseRepository
): ViewModelProvider.Factory {

    companion object {
        fun create(): ViewModelFactoryNew {
            return ViewModelFactoryNew(
                BaseRepository(
                    RemoteDataApiNew.instance
                )
            )
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(repository.create(AuthRepositoryNew::class.java)) as T

           modelClass.isAssignableFrom(HomeViewModel::class.java) ->
               HomeViewModel(repository.create(HomeRepository::class.java)) as T
            else -> throw ClassNotFoundException()
        }
    }
}