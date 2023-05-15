package jp.zyyx.favme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.splash.SplashFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(SplashFragment(), R.id.fragment_container, ScreenType.SplashFlow.Splash.name)

    }
}