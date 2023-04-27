package jp.zyyx.favme.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(fragment: Fragment,containerId: Int, tag: String? = null) {
    supportFragmentManager.beginTransaction().replace(containerId, fragment).addToBackStack(tag).commit()
}