package com.example.android.architecture.blueprints.beetv.modules.movie_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.adapter.MenuAdapter
import com.example.android.architecture.blueprints.beetv.data.models.Category
import com.example.android.architecture.blueprints.beetv.data.models.Movie
import com.example.android.architecture.blueprints.beetv.databinding.FragmentMovieDetailBinding
import com.example.android.architecture.blueprints.beetv.util.getViewModelFactory
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl

class MovieDetailFragment : Fragment() {
    private val viewModel by viewModels<MovieDetailViewModel> { getViewModelFactory() }

    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var viewDataBinding: FragmentMovieDetailBinding

    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private var total: String =""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentMovieDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false

        metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
        metroViewBorderImpl.attachTo(viewDataBinding.main)
        metroViewBorderImpl.attachTo(viewDataBinding.rvChapter)
        metroViewBorderImpl.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {

                changeBackgroundButton(oldFocus, newFocus)
                metroViewBorderImpl.getView().setTag(newFocus)
                val tag = newFocus?.tag as? Pair<*, *>
                if (tag != null) {
                    val position = if ((tag.first as Int).plus(1) < 10) "0" + (tag.first as Int).plus(1) else (tag.first as Int).plus(1).toString()
                    updateNumber(position, total)
                }

            }
        })
        display(Movie.mocks().get(0))
        setupGUI()
    }

    fun setupGUI() {
        viewDataBinding.btPlay.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentDestToPlayerFragment(args.movieID)
            findNavController().navigate(action)
        }
    }

    private fun changeBackgroundButton(oldView: View?, newView: View?) {

        if (oldView != null)
        {
            if (oldView is LinearLayout) {
                if (oldView.getChildAt(0) is TextView) {
                    (oldView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.alto))
                }
            }
            if (oldView is MetroItemFrameLayout) {
                oldView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.black))
                if (oldView.getChildAt(0) is TextView) {
                    (oldView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.alto))
                }
            }
        }

        if (newView != null){
            if (newView is MetroItemFrameLayout) {
                newView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.alto))
                if (newView.getChildAt(0) is TextView) {
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.black))
                }
            }
            if (newView is LinearLayout) {

                if (newView.getChildAt(0) is TextView) {
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.sunsetOrange))
                }
            }
        }



    }

    private fun display(it: Movie){
        Glide.with(context!!).load(it.coverImage).into(viewDataBinding.ivCover)
        viewDataBinding.tvName.text = it.name
        viewDataBinding.tvScore.text = it.score.toString()
        displayChapter(Category.mocksChapterMovie())

    }

    private fun displayChapter(category: MutableList<Category>){
        val adapter = MenuAdapter(category, context!!)
        viewDataBinding.rvChapter.layoutManager = GridLayoutManager(context,1)

        viewDataBinding.rvChapter.adapter =adapter
        total = if (category.size < 10) "0" + category.size else category.size.toString()
        updateNumber("01", total)

    }
    private fun updateNumber(currentPosition: String, totalPage: String) {
        viewDataBinding.tvNumber.text = "${currentPosition}/ ${totalPage} "
    }
}