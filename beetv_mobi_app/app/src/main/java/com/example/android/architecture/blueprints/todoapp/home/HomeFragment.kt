package com.example.android.architecture.blueprints.todoapp.home

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.architecture.blueprints.todoapp.EventObserver
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.adapter.TopMovieAdapter
import com.example.android.architecture.blueprints.todoapp.base.BaseFragment
import com.example.android.architecture.blueprints.todoapp.data.Movie
import com.example.android.architecture.blueprints.todoapp.databinding.FragmentHomeBinding
import com.example.android.architecture.blueprints.todoapp.dialogs.NotiDialog
import com.example.android.architecture.blueprints.todoapp.player.ExoPlayerActivity
import com.example.android.architecture.blueprints.todoapp.util.Constants
import com.example.android.architecture.blueprints.todoapp.util.getViewModelFactory
import com.example.android.architecture.blueprints.todoapp.widgets.CategoryItemView
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderImpl
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment() {
    val viewModel by viewModels<HomeViewModel> { getViewModelFactory() }
    val TAG = "Home Fragment"

    private lateinit var viewDataBinding: FragmentHomeBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            click = ClickProxy(viewModel, this@HomeFragment)
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }
    private var lastView : View ?= null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
//        showDialog()
        showTime()
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false


        val metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
        metroViewBorderImpl.setBackgroundResource(R.drawable.border_color)
        val metroViewBorderImpl2 = MetroViewBorderImpl(context)
        metroViewBorderImpl2.setBackgroundResource(R.drawable.border_color_red)
        metroViewBorderImpl.attachTo(viewDataBinding.list)
        metroViewBorderImpl2.attachTo(viewDataBinding.rvMovie)
        metroViewBorderImpl.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                metroViewBorderImpl.getView().setTag(newFocus)
                changeBackgroundButton(oldFocus, newFocus)
                lastView = newFocus
            }
        })

        metroViewBorderImpl2.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {


            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                if (lastView != null){
                    changeBackgroundButton(lastView, null)
                    lastView = null
                }
            }
        })
        metroViewBorderImpl.getViewBorder().addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                val t: View? = metroViewBorderImpl.getView().findViewWithTag("top")
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
        setupNavigation()
    }

    private fun changeBackgroundButton(oldView: View?, newView: View?) {

        if(oldView != null)
        if (oldView is MetroItemFrameLayout) {
            if (oldView.getChildAt(0) is CategoryItemView) {
                (oldView.getChildAt(0) as CategoryItemView).setColor(R.color.mineShaft)
            }
        }
        if(newView != null)
        if (newView is MetroItemFrameLayout) {
            if (newView.getChildAt(0) is CategoryItemView) {
                (newView.getChildAt(0) as CategoryItemView).setColor(R.color.alto)
            }
        }


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
        val widthItem = context!!.resources.getDimensionPixelOffset(R.dimen.size_150)
        val heightItem = widthItem*406/280
        val movieAdapter = TopMovieAdapter(Movie.mocks(), context!!,widthItem,heightItem)
        movieAdapter.mOnItemClickListener = ({
            Log.d(TAG, it.title)
            val intent = Intent(activity, ExoPlayerActivity::class.java)
            startActivity(intent)
        })
        viewDataBinding.rvMovie.adapter = movieAdapter
        viewDataBinding.rvMovie.scrollToPosition(0)
    }

    private fun setupNavigation() {
        viewModel.openMenuEvent.observe(this, EventObserver {
            openMenu(it)
        })

    }

    private fun openMenu(category: String) {
        if (category?.equals(Constants.TYPE_CATEGORY.ENTERTAINMENT.name)) {
//            val action = HomeFragmentDirections.actionHomeFragmentDestToPlayerFragment(Movie.mocks().first())
//            findNavController().navigate(action)

            val intent = Intent(activity, ExoPlayerActivity::class.java)
            startActivity(intent)
        } else {
            val action = HomeFragmentDirections.actionHomeFragmentDestToMenuFragmentDest(category)
            findNavController().navigate(action)
        }
    }

    private fun showDialog(){
        val dialog = NotiDialog()
        dialog.show(childFragmentManager,"abc")
    }

    public class ClickProxy(val viewModel: HomeViewModel, val fragment : HomeFragment) {
        fun openSearch() {
            fragment.findNavController ().navigate(HomeFragmentDirections.actionHomeFragmentDestToSearchFragmentDest())
        }

        fun openFavorite() {

        }

        fun openSetting() {

        }

        fun openPlayback() {

        }

        fun openLiveMenu() {

            viewModel.openMenu(Constants.TYPE_CATEGORY.TV.name)
        }

        fun openMovieMenu() {

            viewModel.openMenu(Constants.TYPE_CATEGORY.MOVIE.name)
        }

        fun openDramaMenu() {

            viewModel.openMenu(Constants.TYPE_CATEGORY.DRAMA.name)
        }

        fun openEntertainmentMenu() {

            viewModel.openMenu(Constants.TYPE_CATEGORY.ENTERTAINMENT.name)
        }

        fun openEducationMenu() {

            viewModel.openMenu(Constants.TYPE_CATEGORY.EDUCATION.name)
        }

        fun openChildrenMenu() {
            viewModel.openMenu(Constants.TYPE_CATEGORY.CHILDRENTV.name)

        }

    }

}