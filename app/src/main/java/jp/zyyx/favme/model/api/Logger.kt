package jp.zyyx.favme.model.api

import android.util.Log
import jp.zyyx.favme.BuildConfig

object Logger {

    fun printDebug(message: String) {
        if (BuildConfig.DEBUG) {
            val stackTraceElement = Throwable().stackTrace[1]
            Log.d(
                "test",
                "(" + stackTraceElement.className + ":" + stackTraceElement.lineNumber + ")" + message
            )
        }
    }

    fun printError(message: String) {
        if (BuildConfig.DEBUG) {
            val stackTraceElement = Throwable().stackTrace[1]
            Log.e(
                "test",
                "(" + stackTraceElement.className + ":" + stackTraceElement.lineNumber + ")" + message
            )
        }
    }
}