package com.example.android.architecture.blueprints.todoapp.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.android.architecture.blueprints.todoapp.R

class NotiDialog : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_noti)
        val btConfirm = dialog.findViewById<Button>(R.id.bt_confirm)
        btConfirm.setOnClickListener {
            dismiss()
        }
        dialog.show()
        return dialog


    }
}