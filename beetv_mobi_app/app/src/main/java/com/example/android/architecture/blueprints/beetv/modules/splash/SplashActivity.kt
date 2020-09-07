package com.example.android.architecture.blueprints.beetv.modules.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.modules.ads.AdsActivity
import com.example.android.architecture.blueprints.beetv.util.getViewModelFactory

class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel> { getViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashActivity, AdsActivity::class.java))
            finish()
        }, 2000)
    }


}