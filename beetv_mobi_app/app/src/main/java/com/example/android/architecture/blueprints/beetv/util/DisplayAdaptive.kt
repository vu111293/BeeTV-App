package com.example.android.architecture.blueprints.beetv.util

import android.util.TypedValue
import com.example.android.architecture.blueprints.beetv.BeeTVApplication

class DisplayAdaptive {

    private object DisplayAdaptiveHolder {
        val INSTANCE: DisplayAdaptive = DisplayAdaptive()
    }

    private fun DisplayAdaptive() {}

    fun getInstance(): DisplayAdaptive? {
        return DisplayAdaptiveHolder.INSTANCE
    }

    fun toLocalPx(pt: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, pt, BeeTVApplication.context.getResources().getDisplayMetrics())
    }

}