package com.example.android.architecture.blueprints.beetv.modules.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.models.Status
import com.example.android.architecture.blueprints.beetv.manager.ADSManager
import com.example.android.architecture.blueprints.beetv.manager.CategoryManager
import com.example.android.architecture.blueprints.beetv.manager.MovieManager
import com.example.android.architecture.blueprints.beetv.manager.NoticeManager
import com.example.android.architecture.blueprints.beetv.modules.ads.AdsActivity
import com.example.android.architecture.blueprints.beetv.modules.home.HomeActivity
import com.example.android.architecture.blueprints.beetv.util.getViewModelFactory

class SplashActivity : AppCompatActivity() {
    val TAG = SplashActivity::class.java.name
    private val viewModel by viewModels<SplashViewModel> { getViewModelFactory() }
    private var isLoadCategories = false
    private var isLoadTopMovie = false
    private var isLoadNotice = false
    private var isLoadADS = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

        getData()
    }


    private fun getData(){
        viewModel.getCategories().observe(this, Observer {
            it -> run {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "Get category success ")
                    CategoryManager.getInstance().setData(it.data?.results?.objects?.rows)
                    isLoadCategories = true
                    checkLoadData()
                }
                Status.LOADING -> {
                    Log.d(TAG, "Get category loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "Get category error ")
                }
            }
        }
        })

        viewModel.getTopMovie().observe(this, Observer {
            it -> run {
            when (it.status) {
                Status.SUCCESS -> {
                    MovieManager.getInstance().setData(it.data?.results?.objects?.rows)
                    isLoadTopMovie = true
                    checkLoadData()
                    Log.d(TAG, "Get top movie success ")
                }
                Status.LOADING -> {
                    Log.d(TAG, "Get top movie loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "Get top movie error ")
                }
            }
        }
        })




        viewModel.getAdsList().observe(this, Observer {
            it -> run {
            when (it.status) {
                Status.SUCCESS -> {
                    isLoadADS = true
                    ADSManager.getInstance().setData(it.data?.results?.objects?.rows)

                    checkLoadData()
                    Log.d(TAG, "Get ads success ")
                }
                Status.LOADING -> {
                    Log.d(TAG, "Get ads loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "Get ads error ")
                }
            }
        }
        })

        viewModel.getNoticeList().observe(this, Observer {
            it -> run {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "Get notice success ")
                    NoticeManager.getInstance().setData(it.data?.results?.objects?.rows)
                    isLoadNotice = true

                    checkLoadData()
                }
                Status.LOADING -> {
                    Log.d(TAG, "Get notice loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "Get notice error ")
                }
            }
        }
        })
    }

    private fun checkLoadData() {
        if (isLoadCategories && isLoadTopMovie  && isLoadNotice && isLoadADS){

            startActivity(Intent(this@SplashActivity, AdsActivity::class.java))
            finish()
        }

    }
}