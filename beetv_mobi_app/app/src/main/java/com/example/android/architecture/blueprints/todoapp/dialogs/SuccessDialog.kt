package com.example.android.architecture.blueprints.todoapp.dialogs

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.widgets.KeyboardItemView
import com.example.android.architecture.blueprints.todoapp.widgets.SettingItemView
import com.example.android.architecture.blueprints.todoapp.widgets.metro.DrawingOrderRelativeLayout
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderImpl

class SuccessDialog : DialogFragment() {


    var title: String ?= null
    var icon: Int ?= null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_success)

        val tvTitle = dialog.findViewById<TextView>(R.id.tv_title)
        val ivIcon = dialog.findViewById<ImageView>(R.id.iv_icon)

        title?.apply {
            tvTitle.text = this
        }
        icon?.apply {
            ivIcon.setImageResource(this)
        }

        Handler().postDelayed(Runnable {
           dismiss()
        }, 2000)
        return dialog


    }


}