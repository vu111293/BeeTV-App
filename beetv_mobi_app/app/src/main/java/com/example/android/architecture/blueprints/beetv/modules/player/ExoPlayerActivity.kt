package com.example.android.architecture.blueprints.beetv.modules.player

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class ExoPlayerActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, ExoPlayerFragment())
                .commit()
    }
}