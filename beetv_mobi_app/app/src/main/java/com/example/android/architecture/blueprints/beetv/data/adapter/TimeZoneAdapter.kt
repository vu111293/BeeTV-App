package com.example.android.architecture.blueprints.beetv.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.util.formatOffset
import java.util.*

class TimeZoneAdapter(val list: MutableList<TimeZone>) : RecyclerView.Adapter<TimeZoneAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_timezone, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        holder.tvTimeZone.text = item.rawOffset.formatOffset() + " " + item.id
        holder.itemView.tag = item
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTimeZone = view.findViewById<TextView>(R.id.tv_time)
    }
}