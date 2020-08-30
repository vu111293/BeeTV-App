package com.example.android.architecture.blueprints.todoapp.menu

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.example.android.architecture.blueprints.todoapp.EventObserver
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.adapter.MenuAdapter
import com.example.android.architecture.blueprints.todoapp.adapter.TopMovieAdapter
import com.example.android.architecture.blueprints.todoapp.base.BaseFragment
import com.example.android.architecture.blueprints.todoapp.data.Category
import com.example.android.architecture.blueprints.todoapp.data.Movie
import com.example.android.architecture.blueprints.todoapp.databinding.FragmentMenuBinding
import com.example.android.architecture.blueprints.todoapp.home.HomeFragmentDirections
import com.example.android.architecture.blueprints.todoapp.util.Constants
import com.example.android.architecture.blueprints.todoapp.util.getViewModelFactory
import com.example.android.architecture.blueprints.todoapp.util.hide
import com.example.android.architecture.blueprints.todoapp.util.show
import com.example.android.architecture.blueprints.todoapp.widgets.AutoLayoutManager
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderImpl
import java.text.SimpleDateFormat
import java.util.*

class MenuFragment : BaseFragment() {
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
    private val args: MenuFragmentArgs by navArgs()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMenuBinding.inflate(inflater, container, false).apply {
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

        } else {
            viewDataBinding.dynamicList.hide()
            metroViewBorderImpl.attachTo(viewDataBinding.list)
            metroViewBorderImpl2.attachTo(viewDataBinding.rvDetailList)

        }

        viewDataBinding.rvMenuItem.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!recyclerView.canScrollVertically(-1)){
                    viewDataBinding.ivTop.hide()
                } else {
                    viewDataBinding.ivTop.show()
                }

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    viewDataBinding.ivBottom.hide()

                }else{
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
                val tag = newFocus?.tag as? Category
                if (tag != null) {
                    if (args.category == Constants.TYPE_CATEGORY.TV.name){
                        if (isInit){
                            val recyclerView = viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.CHANNEL, Category.mocksChannel())
                            metroViewBorderImpl4.attachTo(recyclerView)
                        }else{
                            isInit = true
                        }

                    }else{
                        getListMovie(tag)
                    }

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
        viewDataBinding.rvMenuItem.layoutManager = GridLayoutManager(context,1)
        viewDataBinding.rvMenuItem.adapter = mAdapter
        if (args.category == Constants.TYPE_CATEGORY.TV.name) {
            val recyclerView = viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.CHANNEL, Category.mocksChannel())
            metroViewBorderImpl4.attachTo(recyclerView)
        } else {
            getListMovie(Category.mocks().get(0))
        }


    }


    private fun getListMovie(category: Category) {
        val gridlayoutManager: GridLayoutManager = AutoLayoutManager(context, 5)
        gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL)
        viewDataBinding.rvDetailList.setLayoutManager(gridlayoutManager)
        metroViewBorderImpl.attachTo(viewDataBinding.rvDetailList)
        val widthItem = context!!.resources.getDimensionPixelOffset(R.dimen.size_130)
        val heightItem = widthItem*377/260
        val movieAdapter = TopMovieAdapter(Movie.mocks(), context!!,widthItem,heightItem)

        movieAdapter.mOnItemClickListener={
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

    private fun openMoviewDetail(movieID: String) {
        val action = MenuFragmentDirections.actionMenuFragmentDestToMovieDetailFragmentDest(movieID)
        findNavController().navigate(action)
    }

    private fun setupNavigation() {
        viewModel.openMovieDetailEvent.observe(this, EventObserver {
            openMoviewDetail(it)
        })

    }
}