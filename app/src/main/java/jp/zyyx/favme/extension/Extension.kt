package jp.zyyx.favme.extension

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}