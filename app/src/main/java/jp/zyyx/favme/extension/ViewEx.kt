package jp.zyyx.favme.extension

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.widget.Toast


private const val RATIO_SCALE_CARD_VIEW_HOR = 1.15F
private const val DURATION_SCAlE_NORMAN = 200L
private var lastClickTime = 0L
private const val THRESHOLD_DOUBLE_TIME = 500


fun View.setOnClickPreventingDouble(onClick: () -> Unit) {
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < THRESHOLD_DOUBLE_TIME) {
            return@setOnClickListener
        }
        onClick.invoke()
        lastClickTime = SystemClock.elapsedRealtime()
    }
}


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.focusedAnim() {
    this.animate().withLayer().scaleX(RATIO_SCALE_CARD_VIEW_HOR).scaleY(RATIO_SCALE_CARD_VIEW_HOR)
        .setDuration(
            DURATION_SCAlE_NORMAN
        ).start()
}