package com.example.android.architecture.blueprints.beetv.modules.home

import android.animation.Animator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.architecture.blueprints.beetv.BeeTVApplication
import com.example.android.architecture.blueprints.beetv.EventObserver
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.common.basegui.BaseFragment
import com.example.android.architecture.blueprints.beetv.data.adapter.TopMovieAdapter
import com.example.android.architecture.blueprints.beetv.data.models.BaseResponse
import com.example.android.architecture.blueprints.beetv.data.models.LiveModel
import com.example.android.architecture.blueprints.beetv.data.models.Movie
import com.example.android.architecture.blueprints.beetv.data.models.Status
import com.example.android.architecture.blueprints.beetv.databinding.FragmentHomeBinding
import com.example.android.architecture.blueprints.beetv.modules.dialogs.NotiDialog
import com.example.android.architecture.blueprints.beetv.modules.dialogs.SettingDialog
import com.example.android.architecture.blueprints.beetv.modules.dialogs.SuccessDialog
import com.example.android.architecture.blueprints.beetv.util.Constants
import com.example.android.architecture.blueprints.beetv.util.getViewModelFactory
import com.example.android.architecture.blueprints.beetv.widgets.CategoryItemView
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment() {
    val viewModel by viewModels<HomeViewModel> { getViewModelFactory() }
    val TAG = "Home Fragment"

    private val args: HomeFragmentArgs by navArgs()
    private lateinit var viewDataBinding: FragmentHomeBinding
    public var lastView: View? = null

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

    private fun setupGUI() {
        viewModel.getLiveList().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let { resource -> {
                Log.d(TAG, "Get top movie status " + resource.status)
               when(resource.status) {
                   Status.SUCCESS -> {
                       resource.data?.let { movie  -> retrieveTopLiveList(movie) }

                   }

                   Status.LOADING -> {

                   }

                   Status.ERROR -> {

                   }
               }
            }}
        })
    }

    private fun retrieveTopLiveList(list: BaseResponse<LiveModel>) {
        Log.d(TAG, "movie number: " + list.results.objects.rows.size)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupGUI()
        showDialog()
        showTime()
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false



        val metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
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
                if (lastView != null) {
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

    public fun changeBackgroundButton(oldView: View?, newView: View?) {

        if (oldView != null)
            if (oldView is MetroItemFrameLayout) {
                if (oldView.getChildAt(0) is CategoryItemView) {
                    (oldView.getChildAt(0) as CategoryItemView).setColor(R.color.mineShaft)
                }
            }
        if (newView != null)
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
        val heightItem = widthItem * 406 / 280
        val movieAdapter = TopMovieAdapter(Movie.mocks(), context!!, widthItem, heightItem)
        movieAdapter.mOnItemClickListener = ({
            Log.d(TAG, it.title)
//            val intent = Intent(activity, ExoPlayerActivity::class.java)
//            startActivity(intent)
            val action = HomeFragmentDirections.actionHomeFragmentDestToMovieDetailFragmentDest(it.id)
            findNavController().navigate(action)
        })
        viewDataBinding.rvMovie.adapter = movieAdapter
        viewDataBinding.rvMovie.scrollToPosition(0)
    }

    private fun setupNavigation() {
        viewModel.openMenuEvent.observe(viewLifecycleOwner, EventObserver {
            openMenu(it)
        })

    }

    private fun openMenu(category: String) {
        val action = HomeFragmentDirections.actionHomeFragmentDestToMenuFragmentDest(category)
        findNavController().navigate(action)
    }

    @SuppressLint("LogNotTimber")
    private fun showDialog() {
        if (!BeeTVApplication.isShowPopup) {
            val dialog = NotiDialog()
            dialog.show(childFragmentManager, "abc")
            BeeTVApplication.isShowPopup = true
        }

          if (!arguments?.getString("type").isNullOrEmpty()) {
            if (arguments?.getString("type").equals(Constants.REGISTER)) {
                val successDialog = SuccessDialog()
                successDialog.icon = R.drawable.ic_register_success
                successDialog.title = getString(R.string.register_successfully)
                successDialog.show(childFragmentManager, "success")
            }

            if (arguments?.getString("type")                                                                                                                                                                                                                                                                                                                         .equals(Constants.LOGIN)) {
                val successDialog = SuccessDialog()
                successDialog.show(childFragmentManager, "success")
            }
            arguments?.remove("type")
        }
    }


    public class ClickProxy(val viewModel: HomeViewModel, val fragment: HomeFragment) {
        fun openSearch() {
            fragment.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentDestToSearchFragmentDest())
        }

        fun openFavorite() {
            val action = HomeFragmentDirections.actionHomeFragmentDestToFavoriteFragmentDest(Constants.TYPE_CATEGORY.FAVORITE.toString())
            fragment.findNavController().navigate(action)
        }

        fun openSetting() {
            val settingDialog = SettingDialog()
            settingDialog.show(fragment.childFragmentManager, "setting")
            settingDialog.onClickLoginListener = {
                fragment.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentDestToLoginFragmentDest())
            }

        }

        fun openPlayback() {
            val action = HomeFragmentDirections.actionHomeFragmentDestToFavoriteFragmentDest(Constants.TYPE_CATEGORY.PLAYBACK.toString())
            fragment.findNavController().navigate(action)
        }

        fun openLiveMenu() {

            fragment.changeBackgroundButton(fragment.lastView, null)
            viewModel.openMenu(Constants.TYPE_CATEGORY.TV.name)

        }

        fun openMovieMenu() {

            fragment.changeBackgroundButton(fragment.lastView, null)
            viewModel.openMenu(Constants.TYPE_CATEGORY.MOVIE.name)
        }

        fun openDramaMenu() {

            fragment.changeBackgroundButton(fragment.lastView, null)
            viewModel.openMenu(Constants.TYPE_CATEGORY.DRAMA.name)
        }

        fun openEntertainmentMenu() {

            fragment.changeBackgroundButton(fragment.lastView, null)
            viewModel.openMenu(Constants.TYPE_CATEGORY.ENTERTAINMENT.name)
        }

        fun openEducationMenu() {

            fragment.changeBackgroundButton(fragment.lastView, null)
            viewModel.openMenu(Constants.TYPE_CATEGORY.EDUCATION.name)
        }

        fun openChildrenMenu() {

            fragment.changeBackgroundButton(fragment.lastView, null)
            viewModel.openMenu(Constants.TYPE_CATEGORY.CHILDRENTV.name)

        }

    }


}