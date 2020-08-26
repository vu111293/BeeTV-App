package com.example.android.architecture.blueprints.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Movie

class TopMovieAdapter(val movieList: MutableList<Movie>, val context : Context) : RecyclerView.Adapter<TopMovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieList.get(position)
        Glide.with(context).load(item.coverImage).into(holder.ivIcon)
        holder.tvName.text = item.name
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon = view.findViewById<ImageView>(R.id.iv_cover)
        val tvName = view.findViewById<TextView>(R.id.tv_name)
    }
}