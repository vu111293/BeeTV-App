package com.example.android.architecture.blueprints.beetv.modules.menu

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.beetv.EventObserver
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.adapter.MenuAdapter
import com.example.android.architecture.blueprints.beetv.data.adapter.TopMovieAdapter
import com.example.android.architecture.blueprints.beetv.common.basegui.BaseFragment
import com.example.android.architecture.blueprints.beetv.data.models.Category
import com.example.android.architecture.blueprints.beetv.data.models.Movie
import com.example.android.architecture.blueprints.beetv.databinding.FragmentMenuBinding
import com.example.android.architecture.blueprints.beetv.modules.dialogs.SettingDialog
import com.example.android.architecture.blueprints.beetv.modules.home.HomeFragment
import com.example.android.architecture.blueprints.beetv.modules.home.HomeFragmentDirections
import com.example.android.architecture.blueprints.beetv.modules.home.HomeViewModel
import com.example.android.architecture.blueprints.beetv.util.Constants
import com.example.android.architecture.blueprints.beetv.util.getViewModelFactory
import com.example.android.architecture.blueprints.beetv.util.hide
import com.example.android.architecture.blueprints.beetv.util.show
import com.example.android.architecture.blueprints.beetv.widgets.AutoLayoutManager
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl
import java.text.SimpleDateFormat
import java.util.*

class MenuFragment : BaseFragment() {

    val TAG = "MenuFragment"
    private val viewModel by viewModels<MenuViewModel> { getViewModelFactory() }
    private lateinit var viewDataBinding: FragmentMenuBinding
    private lateinit var mAdapter: MenuAdapter
    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    private lateinit var metroViewBorderImpl2: MetroViewBorderImpl
    private lateinit var metroViewBorderImpl3: MetroViewBorderImpl
    private lateinit var metroViewBorderImpl4: MetroViewBorderImpl
    private lateinit var metroViewBorderImpl5: MetroViewBorderImpl
    private lateinit var metroViewBorderImpl6: MetroViewBorderImpl

    private var lastView3: View? = null
    private var lastView4: View? = null
    private var lastView5: View? = null
    private var lastView6: View? = null
    private var isInit = false
    private var total: String = ""
    private val args: MenuFragmentArgs by navArgs()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMenuBinding.inflate(inflater, container, false).apply {
            click = ClickProxy(this@MenuFragment)
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        metroViewBorderImpl = MetroViewBorderImpl(context)
        metroViewBorderImpl3 = MetroViewBorderImpl(context)
        metroViewBorderImpl4 = MetroViewBorderImpl(context)
        metroViewBorderImpl5 = MetroViewBorderImpl(context)
        metroViewBorderImpl6 = MetroViewBorderImpl(context)
        metroViewBorderImpl2 = MetroViewBorderImpl(context)
        metroViewBorderImpl2.setBackgroundResource(R.drawable.border_color_red)

        getListMenu()
        display()
        showTime()
        setListener()
        setupNavigation()
    }

    private fun display() {
        metroViewBorderImpl3.attachTo(viewDataBinding.rvMenuItem)
        if (args.category == Constants.TYPE_CATEGORY.TV.name) {
            viewDataBinding.list.hide()
            viewDataBinding.btSearch.isFocusable = false
            viewDataBinding.btSetting.isFocusable = false
            viewDataBinding.btPlayback.isFocusable = false
            viewDataBinding.btFavorite.isFocusable = false

        } else {
            viewDataBinding.dynamicList.hide()
            metroViewBorderImpl.attachTo(viewDataBinding.list)
            metroViewBorderImpl2.attachTo(viewDataBinding.rvDetailList)

        }

        viewDataBinding.rvMenuItem.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(-1)) {
                    viewDataBinding.ivTop.hide()
                } else {
                    viewDataBinding.ivTop.show()
                }

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewDataBinding.ivBottom.hide()

                } else {
                    viewDataBinding.ivBottom.show()
                }
            }

        })
        metroViewBorderImpl3.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                if (lastView3 != null) {
                    changeBackgroundButton(lastView3, null)
                }
                lastView3 = newFocus

                changeBackgroundButton(oldFocus, newFocus)
                metroViewBorderImpl3.getView().setTag(newFocus)
                val tag = newFocus?.tag as? Pair<*, *>
                if (tag != null) {
                    if (args.category == Constants.TYPE_CATEGORY.TV.name) {
                        if (isInit) {
                            val recyclerView = viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.CHANNEL, Category.mocksChannel())
                            metroViewBorderImpl4.attachTo(recyclerView)
                        } else {
                            isInit = true
                        }

                    } else {
                        getListMovie((tag.second as Category).movies)
                    }


                    val position = if ((tag.first as Int).plus(1) < 10) "0" + (tag.first as Int).plus(1) else (tag.first as Int).plus(1).toString()
                    updateNumber(position, total)
                }

            }
        })

        metroViewBorderImpl4.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                if (lastView4 != null) {
                    changeBackgroundButtonChannel(lastView4, null)
                }
                lastView4 = newFocus
                changeBackgroundButtonChannel(oldFocus, newFocus)
                metroViewBorderImpl4.getView().setTag(newFocus)
                val tag = newFocus?.tag as? Category
                if (tag != null) {
                    val recyclerView2 = viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.PROGRAM, Category.mocksProgram())
                    metroViewBorderImpl5.attachTo(recyclerView2)
                }
            }
        })
        metroViewBorderImpl5.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {

                if (lastView5 != null) {
                    changeBackgroundButtonProgram(lastView5, null)
                }
                lastView5 = newFocus
                changeBackgroundButtonProgram(oldFocus, newFocus)
                metroViewBorderImpl5.getView().setTag(newFocus)
                val tag = newFocus?.tag as? Category
                if (tag != null) {
                    val recyclerView3 = viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.CHAPTER, Category.mocksChapter())
                    metroViewBorderImpl6.attachTo(recyclerView3)
                }
            }
        })

        metroViewBorderImpl6.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                if (lastView6 != null) {
                    changeBackgroundButton(lastView6, null)
                }
                lastView6 = newFocus
                changeBackgroundButton(oldFocus, newFocus)
                metroViewBorderImpl6.getView().setTag(newFocus)
                val tag = newFocus?.tag as? Category


            }
        })
    }

    private fun changeBackgroundButton(oldView: View?, newView: View?) {

        if (oldView != null)

            if (oldView is LinearLayout) {
                if (oldView.getChildAt(0) is TextView) {
                    (oldView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.alto))
                }
            }
        if (newView != null)
            if (newView is LinearLayout) {

                if (newView.getChildAt(0) is TextView) {
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.sunsetOrange))
                }
            }


    }

    private fun changeBackgroundButtonProgram(oldView: View?, newView: View?) {

        if (oldView != null)
            if (oldView is LinearLayout) {
                if (oldView.getChildAt(0) is TextView) {
                    (oldView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.alto))
                    (oldView.getChildAt(1) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.alto))
                }
            }
        if (newView != null)
            if (newView is LinearLayout) {
                if (newView.getChildAt(0) is TextView) {
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.sunsetOrange))
                    (newView.getChildAt(1) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.sunsetOrange))
                }
            }


    }

    private fun changeBackgroundButtonChannel(oldView: View?, newView: View?) {

        if (oldView != null)
            if (oldView is LinearLayout) {
                if (oldView.getChildAt(0) is LinearLayout) {
                    val viewChildOne = oldView.getChildAt(0) as LinearLayout
                    (viewChildOne.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.alto))
                    (viewChildOne.getChildAt(1) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.alto))

                    val viewChildTwo = oldView.getChildAt(1) as LinearLayout
                    (viewChildTwo.getChildAt(1) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.alto))
                }
            }
        if (newView != null)
            if (newView is LinearLayout) {
                if (newView.getChildAt(0) is LinearLayout) {
                    val viewChildOne = newView.getChildAt(0) as LinearLayout
                    (viewChildOne.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.sunsetOrange))
                    (viewChildOne.getChildAt(1) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.sunsetOrange))
                    val viewChildTwo = newView.getChildAt(1) as LinearLayout
                    (viewChildTwo.getChildAt(1) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.sunsetOrange))
                }
            }


    }

    private fun getListMenu() {
        mAdapter = MenuAdapter(Category.mocks(), context!!)
        viewDataBinding.rvMenuItem.layoutManager = GridLayoutManager(context, 1)
        viewDataBinding.rvMenuItem.adapter = mAdapter
        if (args.category == Constants.TYPE_CATEGORY.TV.name) {
            val recyclerView = viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.CHANNEL, Category.mocksChannel())
            metroViewBorderImpl4.attachTo(recyclerView)
        } else {
            getListMovie(Category.mocks().get(0).movies)
        }
        total = if (Category.mocks().size < 10) "0" + Category.mocks().size else Category.mocks().size.toString()
        updateNumber("01", total)
    }


    private fun getListMovie(movie: MutableList<Movie>) {
        val gridlayoutManager: GridLayoutManager = AutoLayoutManager(context, 5)
        gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL)
        viewDataBinding.rvDetailList.setLayoutManager(gridlayoutManager)
        metroViewBorderImpl.attachTo(viewDataBinding.rvDetailList)
        val widthItem = context!!.resources.getDimensionPixelOffset(R.dimen.size_130)
        val heightItem = widthItem * 377 / 260
        val movieAdapter = TopMovieAdapter(movie, context!!, widthItem, heightItem)

        movieAdapter.mOnItemClickListener = {
            viewModel.openMovieDetail(it.id)
        }
        viewDataBinding.rvDetailList.adapter = movieAdapter

    }

    private fun showTime() {
        val someHandler = Handler(Looper.getMainLooper())
        someHandler.postDelayed(object : Runnable {
            override fun run() {
                viewDataBinding.tvClock.setText(SimpleDateFormat("HH:mm").format(Date()))
                someHandler.postDelayed(this, 1000)
            }
        }, 10)
        viewDataBinding.tvDate.setText(SimpleDateFormat("EEEE").format(Date()) + "\n" + SimpleDateFormat("yyyy.MM.dd").format(Date()))
    }


    private fun setListener() {
        viewDataBinding.dynamicList.mOnClickItemListener = { typeMenu: Constants.TYPE_MENU, category: Category ->
            when (typeMenu) {
                Constants.TYPE_MENU.CHANNEL -> {
                    // Todo pass zero for trick play default live channel
                    openLiveChannel("0")
                }

                Constants.TYPE_MENU.PROGRAM -> {

                }

                Constants.TYPE_MENU.CHAPTER -> {

                }

                else->{

                }
            }
        }
        viewDataBinding.dynamicList.mOnRemoveListener = { typeMenu: Constants.TYPE_MENU, recyclerView: RecyclerView ->
//            if (typeMenu == Constants.TYPE_MENU.CHAPTER) {
//                metroViewBorderImpl6.detach()
//            }
//
//            if (typeMenu == Constants.TYPE_MENU.PROGRAM) {
//                metroViewBorderImpl5.detach()
//            }
        }
    }

    private fun openLiveChannel(channelId: String) {
        val action = MenuFragmentDirections.actionMenuFragmentDestToPlayerFragment(channelId)
        findNavController().navigate(action)
    }

    private fun openMovieDetail(movieID: String) {
        val action = MenuFragmentDirections.actionMenuFragmentDestToMovieDetailFragmentDest(movieID)
        findNavController().navigate(action)
    }

    private fun setupNavigation() {
        viewModel.openMovieDetailEvent.observe(viewLifecycleOwner, EventObserver {
            openMovieDetail(it)
        })

    }

    private fun updateNumber(currentPosition: String, totalPage: String) {
        viewDataBinding.tvNumber.text = "${currentPosition}/ ${totalPage} "
    }

    public class ClickProxy( val fragment: MenuFragment) {
        fun openSearch() {
            fragment.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentDestToSearchFragmentDest())
        }

        fun openFavorite() {
            val action = MenuFragmentDirections.actionMenuFragmentDestToFavoriteFragmentDest(Constants.TYPE_CATEGORY.FAVORITE.toString())
            fragment.findNavController().navigate(action)
        }

        fun openSetting() {
            val settingDialog = SettingDialog()
            settingDialog.show(fragment.childFragmentManager, "setting")
            settingDialog.onClickLoginListener = {
                fragment.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentDestToLoginFragmentDest())
            }

        }

        fun openPlayback() {
            val action = HomeFragmentDirections.actionHomeFragmentDestToFavoriteFragmentDest(Constants.TYPE_CATEGORY.PLAYBACK.toString())
            fragment.findNavController().navigate(action)
        }


    }
}