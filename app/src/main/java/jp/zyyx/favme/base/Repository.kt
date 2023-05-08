package jp.zyyx.favme.base

abstract class Repository {

    interface Factory {
        /**
         * Creates a new instance of the given `Class`.
         *
         *
         *
         * @param modelClass a `Class` whose instance is requested
         * @param <T>        The type parameter for the ViewModel.
         * @return a newly created ViewModel
        </T> */
        fun <T : Repository?> create(modelClass: Class<T>): T
    }
}