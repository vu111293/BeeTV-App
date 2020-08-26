package com.example.android.architecture.blueprints.todoapp.home

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.adapter.TopMovieAdapter
import com.example.android.architecture.blueprints.todoapp.base.BaseFragment
import com.example.android.architecture.blueprints.todoapp.data.Movie
import com.example.android.architecture.blueprints.todoapp.databinding.HomeFragmentBinding
import com.example.android.architecture.blueprints.todoapp.util.getViewModelFactory
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderImpl
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
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false

        val metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
        metroViewBorderImpl.setBackgroundResource(R.drawable.border_color)

        metroViewBorderImpl.attachTo(viewDataBinding.list)
        metroViewBorderImpl.attachTo(viewDataBinding.rvMovie)
        metroViewBorderImpl.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {

            override fun onFocusChanged(oldFocus: View, newFocus: View) {
                metroViewBorderImpl.getView().setTag(newFocus)
            }
        })
        metroViewBorderImpl.getViewBorder().addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                val t: View ?= metroViewBorderImpl.getView().findViewWithTag("top")
                if (t != null) {
                    (t.parent as ViewGroup).removeView(t)
                    (metroViewBorderImpl.getView().getTag(metroViewBorderImpl.getView().getId()) as ViewGroup).addView(t)
                }
            }

            override fun onAnimationEnd(animation: Animator) {
                val nf = metroViewBorderImpl.getView().getTag() as? View
                if (nf != null) {
                    val top = nf.findViewWithTag<View>("top")
                    if (top != null) {
                        (top.parent as ViewGroup).removeView(top)
                        (metroViewBorderImpl.getView() as ViewGroup).addView(top)
                        metroViewBorderImpl.getView().setTag(metroViewBorderImpl.getView().getId(), nf)
                    }
                }
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        showMovieList()

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

    private fun showMovieList() {
        val movieAdapter = TopMovieAdapter(Movie.mocks(),context!!)
        viewDataBinding.rvMovie.adapter = movieAdapter

        viewDataBinding.rvMovie.scrollToPosition(0)

    }
}