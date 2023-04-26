package jp.zyyx.favme.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Adapter
import jp.zyyx.favme.R
import jp.zyyx.favme.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        object : CountDownTimer (2000,2000) {
            override fun onTick(millisUntilFinished: Long) {
                startActivity(Intent(this@SplashActivity, IntroduceActivity::class.java))
            }

            override fun onFinish() {

            }

        }.start()

    }



}