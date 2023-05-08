package jp.zyyx.favme.base

import jp.zyyx.favme.model.RemoteDataApiNew
import jp.zyyx.favme.repository.AuthRepositoryNew

class BaseRepositoryNew(
    private val remoteDataAPINew: RemoteDataApiNew
) : Repository.Factory {

    companion object {
        private var authRepository: AuthRepositoryNew? = null
    }

    override fun <T : Repository?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthRepositoryNew::class.java) -> {
                if (authRepository == null) {
                    authRepository = AuthRepositoryNew(remoteDataAPINew)
                }
                authRepository as T
            }
            else -> throw ClassNotFoundException()
        }
    }

}