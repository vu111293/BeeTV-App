package com.example.android.architecture.blueprints.beetv.data.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.models.Movie
import com.example.android.architecture.blueprints.beetv.data.models.Slide
import com.example.android.architecture.blueprints.beetv.util.hide

class SlideAdapter(val slideList: MutableList<Slide>, val context : Context) : RecyclerView.Adapter<SlideAdapter.ViewHolder>() {
    var mOnItemClickListener : ((Movie) -> Unit) ?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_slide, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = slideList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = slideList.get(position)
        Glide.with(context).load(item.image).listener(object :RequestListener<Drawable>{
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                holder.progressBar.hide()
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                holder.progressBar.hide()
                return false
            }

        }).into(holder.ivCover)

        holder.itemView.tag = position

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCover = view.findViewById<ImageView>(R.id.iv_cover)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

    }
}