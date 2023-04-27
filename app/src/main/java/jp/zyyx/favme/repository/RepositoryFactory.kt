//package jp.zyyx.favme.repository
//
//import jp.zyyx.favme.model.RemoteDataSource
//import jp.zyyx.favme.ui.auth.AuthApi
//
//class RepositoryFactory(
//    private val quizApi: RemoteDataSource
//): Repository.Factory {
//
//    companion object {
//        private var authRepository : AuthRepository? = null
//    }
//
//    override fun <T : Repository?> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(AuthRepository::class.java) -> {
//                if (authRepository == null) {
//                    authRepository = AuthRepository(quizApi)
//                }
//                authRepository as T
//            }
//            else -> throw ClassNotFoundException()
//        }
//    }
//}