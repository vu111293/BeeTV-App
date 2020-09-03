package com.example.android.architecture.blueprints.todoapp.splash

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.databinding.FragmentSearchBinding
import com.example.android.architecture.blueprints.todoapp.databinding.FragmentSplashBinding
import com.example.android.architecture.blueprints.todoapp.search.SearchViewModel
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.util.getViewModelFactory

class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel> { getViewModelFactory() }
    private lateinit var viewDataBinding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = FragmentSplashBinding.inflate(LayoutInflater.from(this)).apply {
            viewmodel = viewModel
        }

        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashActivity, TasksActivity::class.java))
            finish()
        }, 2000)
    }


}