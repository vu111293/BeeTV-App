package com.example.android.architecture.blueprints.beetv.common.basegui

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    open fun myOnKeyDown(keyCode: Int) {
    }
}