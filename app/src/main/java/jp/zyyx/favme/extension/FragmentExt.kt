package jp.zyyx.favme.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

//fun AppCompatActivity.replaceFragment(fragment: Fragment,containerId: Int, tag: String? = null) {
//    supportFragmentManager.beginTransaction().replace(containerId, fragment).addToBackStack(tag).commit()
//}

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

enum class TransitionType {
    SIBLING, DETAIL, MODAL
}


inline fun <reified T : Fragment> FragmentManager.handleReplace(
    containerId: Int,
    transitionType: TransitionType? = null,
    fragment: T
) {
    val tag: String = T::class.java.name
    beginTransaction().apply {
        transitionType?.let { setTransition(it) }
        replace(containerId, findFragmentByTag(tag) ?: fragment, tag)
        addToBackStack(tag)
        setReorderingAllowed(true)
        commitAllowingStateLoss()
    }
}

fun FragmentTransaction.setTransition(transitionType: TransitionType) {
//    setCustomAnimations(
//        when (transitionType) {
//            TransitionType.SIBLING -> R.anim.fade_in
//            TransitionType.DETAIL -> R.anim.slide_from_end
//            TransitionType.MODAL -> R.anim.slide_from_bottom
//        },
//        R.anim.fade_out,
//        R.anim.fade_in,
//        when (transitionType) {
//            TransitionType.SIBLING -> R.anim.fade_out
//            TransitionType.DETAIL -> R.anim.slide_to_end
//            TransitionType.MODAL -> R.anim.slide_to_bottom
//        }
//    )
}