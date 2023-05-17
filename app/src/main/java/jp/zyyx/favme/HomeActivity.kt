package jp.zyyx.favme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.zyyx.favme.databinding.ActivityHomeBinding
import jp.zyyx.favme.extension.replaceFragment
import jp.zyyx.favme.navigation.ScreenType

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_home)

        replaceFragment(
            MainFragment(),
            R.id.fragment_container_home,
            ScreenType.HomeFlow.MainFragment.name
        )

    }
}