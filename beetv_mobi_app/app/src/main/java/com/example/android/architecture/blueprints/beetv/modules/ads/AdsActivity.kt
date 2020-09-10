package com.example.android.architecture.blueprints.beetv.modules.ads

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.adapter.SlideAdapter
import com.example.android.architecture.blueprints.beetv.data.models.Slide
import com.example.android.architecture.blueprints.beetv.databinding.ActivityAdsBinding
import com.example.android.architecture.blueprints.beetv.databinding.FragmentHomeBinding
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl
import com.example.android.architecture.blueprints.beetv.modules.home.HomeActivity
import com.example.android.architecture.blueprints.beetv.modules.splash.SplashViewModel
import com.example.android.architecture.blueprints.beetv.util.getViewModelFactory
import timber.log.Timber

class AdsActivity : AppCompatActivity() {


    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    private val viewModel by viewModels<AdsViewModel> { getViewModelFactory() }


    private lateinit var rvImage : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ads)

        metroViewBorderImpl = MetroViewBorderImpl(this)

        rvImage = findViewById(R.id.rv_image)
        metroViewBorderImpl.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                metroViewBorderImpl.getView().setTag(newFocus)
                if (newFocus != null){
                   val position = newFocus.tag as Int
                    if (position == Slide.mocks().size -1){
                        Handler().postDelayed(Runnable {
                            startActivity(Intent(this@AdsActivity, HomeActivity::class.java))
                            finish()
                        }, 2000)
                    }
                }
            }
        })
        metroViewBorderImpl.attachTo(rvImage)
        setupListAdapter()
    }

    private fun setupListAdapter() {
        val adapter = SlideAdapter(viewModel)
        rvImage.adapter = adapter
        viewModel.adsList.observe(this){
            adapter.submitList(it)
        }

    }
}