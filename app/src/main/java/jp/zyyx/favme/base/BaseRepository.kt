package jp.zyyx.favme.base

import jp.zyyx.favme.model.RemoteDataApiNew
import jp.zyyx.favme.repository.AuthRepositoryNew
import jp.zyyx.favme.repository.HomeRepository

class BaseRepository (
    private val remoteDataAPINew: RemoteDataApiNew
) : Repository.Factory {

    companion object {
        private var authRepository: AuthRepositoryNew? = null
        private var homeRepository: HomeRepository? = null
    }

    override fun <T : Repository?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthRepositoryNew::class.java) -> {
                if (authRepository == null) {
                    authRepository = AuthRepositoryNew(remoteDataAPINew)
                }
                authRepository as T
            }

            modelClass.isAssignableFrom(HomeRepository::class.java) -> {
                if (homeRepository == null) {
                    homeRepository = HomeRepository(remoteDataAPINew)
                }
                homeRepository as T
            }
            else -> throw ClassNotFoundException()
        }
    }

}