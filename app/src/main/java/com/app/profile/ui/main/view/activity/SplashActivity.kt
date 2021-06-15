package com.app.profile.ui.main.view.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.app.profile.databinding.ActivitySplashBinding
import com.app.profile.utils.AppUtils

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    private fun startSplashCounter() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                AppUtils.startActivity(this@SplashActivity, LoginActivity::class.java, null, true)
            }

            override fun onTick(p0: Long) {

            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.hide()
        AppUtils.hideSystemUI(window, binding.mainContainer)
        startSplashCounter()
    }
}