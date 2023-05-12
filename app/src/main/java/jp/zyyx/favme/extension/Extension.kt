package jp.zyyx.favme.extension

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    container: Int,
    tag: String
) {
    val f = supportFragmentManager.findFragmentByTag(tag)
    if (f != null) {
        supportFragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    with(supportFragmentManager.beginTransaction()) {
        replace(container, fragment, tag)
            .addToBackStack(tag)
        setReorderingAllowed(true)
        commitAllowingStateLoss()
    }
}

fun FragmentActivity.popBackStack() = supportFragmentManager.popBackStack()


fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
