package com.example.android.architecture.blueprints.todoapp.player

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.replace

class ExoPlayerActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, ExoPlayerFragment())
                .commit()
    }
}