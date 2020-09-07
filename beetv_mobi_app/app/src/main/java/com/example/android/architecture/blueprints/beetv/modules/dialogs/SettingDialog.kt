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
import com.example.android.architecture.blueprints.beetv.widgets.SettingItemView
import com.example.android.architecture.blueprints.beetv.widgets.metro.DrawingOrderRelativeLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl

class SettingDialog : DialogFragment() {


    var onClickLoginListener : (() -> Unit) ?=null
    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false

        metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_setting)
        val btAuthority = dialog.findViewById<MetroItemFrameLayout>(R.id.bt_authority)
        val btLogout = dialog.findViewById<MetroItemFrameLayout>(R.id.bt_log_out)
        val btLanguage = dialog.findViewById<SettingItemView>(R.id.bt_language)
        val btScreen = dialog.findViewById<SettingItemView>(R.id.bt_screen)
        val btSlot = dialog.findViewById<SettingItemView>(R.id.bt_slot)
        val btPlayer = dialog.findViewById<SettingItemView>(R.id.bt_player)
        val main = dialog.findViewById<DrawingOrderRelativeLayout>(R.id.main)
        btLanguage.setValue(getString(R.string.korean))
        btScreen.setValue("기존비율")
        btPlayer.setValue("시스템 플레이어")
        btSlot.setValue("시스템 시간")
        metroViewBorderImpl.attachTo(main)
        btAuthority.setOnClickListener {
            dismiss()
            onClickLoginListener?.invoke()
        }

        btLogout.setOnClickListener {

            dismiss()
            val logoutDialog = LogoutDialog()
            logoutDialog.show(childFragmentManager,"logout")
        }

        btLanguage.setOnClickListener {
            val languageDialog = LanguageDialog()
            languageDialog.show(childFragmentManager,"language")
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

            if (oldView is SettingItemView){
                oldView.setColor(R.color.black)

            }
        }

        if(newView != null){
            if (newView is MetroItemFrameLayout){
                newView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.alto))
                if (newView.getChildAt(0) is TextView){
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.black))
                }
            }

            if (newView is SettingItemView){
                newView.setColor(R.color.alto)

            }
        }



    }
}