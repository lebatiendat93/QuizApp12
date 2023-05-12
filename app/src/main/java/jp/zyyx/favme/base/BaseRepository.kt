package jp.zyyx.favme.base

import jp.zyyx.favme.model.RemoteDataApi
import jp.zyyx.favme.repository.AuthRepository
import jp.zyyx.favme.repository.HomeRepository

class BaseRepository (
    private val remoteDataAPINew: RemoteDataApi
) : Repository.Factory {

    companion object {
        private var authRepository: AuthRepository? = null
        private var homeRepository: HomeRepository? = null
    }

    override fun <T : Repository?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthRepository::class.java) -> {
                if (authRepository == null) {
                    authRepository = AuthRepository(remoteDataAPINew)
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