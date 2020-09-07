package com.example.android.architecture.blueprints.beetv.modules.ads

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.adapter.SlideAdapter
import com.example.android.architecture.blueprints.beetv.data.models.Slide
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity

class AdsActivity : AppCompatActivity() {


    private lateinit var metroViewBorderImpl: MetroViewBorderImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ads)
        val rvImage = findViewById<RecyclerView>(R.id.rv_image)
        val adapter = SlideAdapter(Slide.mocks(), this)
        rvImage.adapter = adapter

        metroViewBorderImpl = MetroViewBorderImpl(this)

        metroViewBorderImpl.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                metroViewBorderImpl.getView().setTag(newFocus)
                if (newFocus != null){
                   val position = newFocus.tag as Int
                    if (position == Slide.mocks().size -1){
                        Handler().postDelayed(Runnable {
                            startActivity(Intent(this@AdsActivity, TasksActivity::class.java))
                            finish()
                        }, 2000)
                    }
                }
            }
        })
        metroViewBorderImpl.attachTo(rvImage)
    }
}