package com.example.android.architecture.blueprints.beetv.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.models.BAds
import com.example.android.architecture.blueprints.beetv.data.models.BMovie
import com.example.android.architecture.blueprints.beetv.data.models.Movie
import com.example.android.architecture.blueprints.beetv.databinding.ItemSlideBinding
import com.example.android.architecture.blueprints.beetv.databinding.ItemTopMovieBinding
import com.example.android.architecture.blueprints.beetv.modules.ads.AdsViewModel
import com.example.android.architecture.blueprints.beetv.modules.home.HomeViewModel
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroItemFrameLayout

class TopMovieAdapter2(private val viewModel: HomeViewModel,
                      val widthItem : Int, val heightItem : Int) :
        ListAdapter<BMovie, TopMovieAdapter2.ViewHolder>(MovieDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemTopMovieBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: HomeViewModel, item: BMovie,widthItem : Int,  heightItem : Int) {

            binding.viewmodel = viewModel
            binding.movie = item
            binding.executePendingBindings()
            binding.main.layoutParams.width = widthItem
            binding.main.layoutParams.height = heightItem
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTopMovieBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel,item, widthItem,heightItem)

    }
}
class MovieDiffCallback : DiffUtil.ItemCallback<BMovie>() {
    override fun areItemsTheSame(oldItem: BMovie, newItem: BMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BMovie, newItem: BMovie): Boolean {
        return oldItem == newItem
    }
}
