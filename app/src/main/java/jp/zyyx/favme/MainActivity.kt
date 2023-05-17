package jp.zyyx.favme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.zyyx.favme.databinding.ActivityMainBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.navigation.ScreenType
import jp.zyyx.favme.ui.splash.SplashFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        replaceFragment(
            SplashFragment(),
            R.id.fragment_container,
            ScreenType.AuthFlow.Login.name
        )

    }




}