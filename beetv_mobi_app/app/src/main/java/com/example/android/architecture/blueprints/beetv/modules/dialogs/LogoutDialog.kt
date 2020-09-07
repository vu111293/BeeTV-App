package com.example.android.architecture.blueprints.beetv.modules.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.widgets.metro.DrawingOrderRelativeLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl

class LogoutDialog : DialogFragment() {
    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false

        metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_logout)
        val btConfirm = dialog.findViewById<MetroItemFrameLayout>(R.id.bt_confirm)
        val btCancel = dialog.findViewById<MetroItemFrameLayout>(R.id.bt_cancel)
        val main = dialog.findViewById<DrawingOrderRelativeLayout>(R.id.main)
        metroViewBorderImpl.attachTo(main)
        btConfirm.setOnClickListener {
            dismiss()
        }

        btCancel.setOnClickListener {
            dismiss()
        }
        dialog.show()

        metroViewBorderImpl.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                metroViewBorderImpl.getView().setTag(newFocus)
                changeBackgroundButton(oldFocus, newFocus)
            }
        })
        return dialog


    }

    private fun changeBackgroundButton(oldView: View?, newView: View?) {

        if(oldView != null){

            if (oldView is MetroItemFrameLayout){
                oldView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.black))
                if (oldView.getChildAt(0) is TextView){
                    (oldView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!,R.color.white))
                }
            }
        }

        if(newView != null){
            if (newView is MetroItemFrameLayout){
                newView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.white))
                if (newView.getChildAt(0) is TextView){
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.black))
                }
            }
        }



    }
}