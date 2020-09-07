package com.example.android.architecture.blueprints.beetv.modules.dialogs

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.android.architecture.blueprints.beetv.R

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
        }, 10000)
        return dialog


    }


}