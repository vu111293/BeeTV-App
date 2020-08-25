package com.example.android.architecture.blueprints.todoapp.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android.architecture.blueprints.todoapp.base.BaseFragment
import com.example.android.architecture.blueprints.todoapp.databinding.HomeFragmentBinding
import com.example.android.architecture.blueprints.todoapp.util.getViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : BaseFragment() {
    private val viewModel by viewModels<HomeViewModel> { getViewModelFactory() }

    private lateinit var viewDataBinding: HomeFragmentBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = HomeFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        showTime()
    }

    override fun myOnKeyDown(keyCode: Int) {
        super.myOnKeyDown(keyCode)

    }


    private fun showTime() {
        val someHandler = Handler(getMainLooper())
        someHandler.postDelayed(object : Runnable {
            override fun run() {
                viewDataBinding.tvClock.setText(SimpleDateFormat("HH:mm").format(Date()))
                someHandler.postDelayed(this, 1000)
            }
        }, 10)
    }

}