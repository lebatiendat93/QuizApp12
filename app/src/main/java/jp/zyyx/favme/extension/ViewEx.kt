package jp.zyyx.favme.extension

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.widget.Toast



private var lastClickTime = 0L
private const val THRESHOLD_DOUBLE_TIME = 500

fun View.visible(isShow: Boolean) {
    visibility = if (isShow) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.setOnClickPreventingDouble(onClick: () -> Unit) {
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < THRESHOLD_DOUBLE_TIME) {
            return@setOnClickListener
        }
        onClick.invoke()
        lastClickTime = SystemClock.elapsedRealtime()
    }
}