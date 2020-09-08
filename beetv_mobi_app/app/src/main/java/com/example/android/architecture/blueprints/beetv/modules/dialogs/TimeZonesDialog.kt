package com.example.android.architecture.blueprints.beetv.modules.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.adapter.TimeZoneAdapter
import com.example.android.architecture.blueprints.beetv.widgets.metro.DrawingOrderRelativeLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl
import java.util.*
import kotlin.Comparator

class TimeZonesDialog : DialogFragment() {
    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false

        metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_timezones)
        val rvTimeZones = dialog.findViewById<RecyclerView>(R.id.rv_timezone)
        val main = dialog.findViewById<DrawingOrderRelativeLayout>(R.id.main)
        metroViewBorderImpl.attachTo(main)
        metroViewBorderImpl.attachTo(rvTimeZones)
        val ids = TimeZone.getAvailableIDs()
        val zones = mutableListOf<TimeZone>()
        for (id : String in ids) {
            zones.add(TimeZone.getTimeZone(id));
        }
        zones.sortWith(Comparator { timeZone: TimeZone, timeZone1: TimeZone ->  timeZone.rawOffset - timeZone1.rawOffset })

        val adapter = TimeZoneAdapter(zones)
        rvTimeZones.adapter = adapter
        dialog.show()

        metroViewBorderImpl.viewBorder.addOnFocusChanged { oldFocus, newFocus ->
            metroViewBorderImpl.view.tag = newFocus
            changeBackgroundButton(oldFocus, newFocus)
        }
        return dialog


    }

    private fun changeBackgroundButton(oldView: View?, newView: View?) {

        if (oldView != null) {

            if (oldView is MetroItemFrameLayout) {
                oldView.setBackgroundColor(ContextCompat.getColor(context!!, android.R.color.transparent))
                if (oldView.getChildAt(0) is TextView) {
                    (oldView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.white))
                }
            }
        }

        if (newView != null) {
            if (newView is MetroItemFrameLayout) {
                newView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.alto))
                if (newView.getChildAt(0) is TextView) {
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.black))
                }
            }
        }


    }


}