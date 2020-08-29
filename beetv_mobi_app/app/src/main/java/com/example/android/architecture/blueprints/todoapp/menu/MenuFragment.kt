package com.example.android.architecture.blueprints.todoapp.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.adapter.MenuAdapter
import com.example.android.architecture.blueprints.todoapp.adapter.TopMovieAdapter
import com.example.android.architecture.blueprints.todoapp.base.BaseFragment
import com.example.android.architecture.blueprints.todoapp.data.Category
import com.example.android.architecture.blueprints.todoapp.data.Movie
import com.example.android.architecture.blueprints.todoapp.databinding.FragmentMenuBinding
import com.example.android.architecture.blueprints.todoapp.util.Constants
import com.example.android.architecture.blueprints.todoapp.util.getViewModelFactory
import com.example.android.architecture.blueprints.todoapp.util.hide
import com.example.android.architecture.blueprints.todoapp.widgets.AutoLayoutManager
import com.example.android.architecture.blueprints.todoapp.widgets.metro.MetroViewBorderImpl

class MenuFragment : BaseFragment() {
    private val viewModel by viewModels<MenuViewModel> { getViewModelFactory() }
    private lateinit var viewDataBinding: FragmentMenuBinding
    private lateinit var mAdapter: MenuAdapter
    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    private lateinit var metroViewBorderImpl2: MetroViewBorderImpl

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
        metroViewBorderImpl.setBackgroundResource(R.drawable.border_color)
        metroViewBorderImpl2 = MetroViewBorderImpl(context)
        metroViewBorderImpl2.setBackgroundResource(R.drawable.border_color_red)
        display()
        getListMenu()


    }

    private fun display() {

        metroViewBorderImpl.attachTo(viewDataBinding.rvMenuItem)
        if (args.category.equals(Constants.TYPE_CATEGORY.TV.name)) {
            viewDataBinding.list.hide()

        } else {
            viewDataBinding.dynamicList.hide()
            metroViewBorderImpl.attachTo(viewDataBinding.list)
            metroViewBorderImpl2.attachTo(viewDataBinding.rvDetailList)
        }
    }

    private fun getListMenu() {
        mAdapter = MenuAdapter(Category.mocks(), context!!)
        mAdapter.mOnClickListener = {

            if (args.category.equals(Constants.TYPE_CATEGORY.TV.name)) {
                val recyclerView = viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.CHANNEL, Category.mocksChannel())
                viewDataBinding.dynamicList.listener = { typeMenu: Constants.TYPE_MENU, id: String ->
                    val recyclerView2 = if (typeMenu == Constants.TYPE_MENU.CHANNEL) {
                        viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.PROGRAM, Category.mocksProgram())

                    } else {
                        viewDataBinding.dynamicList.initView(Constants.TYPE_MENU.CHAPTER, Category.mocksChapter())
                    }
                    metroViewBorderImpl.attachTo(recyclerView2)
                }
                metroViewBorderImpl.attachTo(recyclerView)
            } else {
                getListMovie()
            }

        }
        viewDataBinding.rvMenuItem.adapter = mAdapter
    }


    private fun getListMovie() {
        val gridlayoutManager: GridLayoutManager = AutoLayoutManager(context, 5)
        gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL)
        viewDataBinding.rvDetailList.setLayoutManager(gridlayoutManager)
        metroViewBorderImpl.attachTo(viewDataBinding.rvDetailList)

        val adapter = TopMovieAdapter(Movie.mocks(), context!!)
        viewDataBinding.rvDetailList.adapter = adapter
    }
}