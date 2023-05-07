package jp.zyyx.favme.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.ui.auth.AuthViewModel

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw ClassNotFoundException()
        }
    }
}