package com.example.android.architecture.blueprints.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Category

class MenuAdapter(val categorys: MutableList<Category>, val context: Context) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    var mOnClickListener : ((Category) -> Unit) ?= null
    var mCategorySelected : Category ?= null
    var positionLast : Int = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categorys.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categorys.get(position)
        holder.tvName.text = item.name
        holder.itemView.setOnClickListener {
            mCategorySelected = item
            holder.tvName.setTextColor(ContextCompat.getColor(context, R.color.sunsetOrange))
            holder.main.setBackgroundColor(ContextCompat.getColor(context, R.color.mineShaft))
            mOnClickListener?.invoke(item)
            val temp = positionLast

            positionLast = position
            if (temp != -1){

                notifyItemChanged(temp)
            }
        }

        if(mCategorySelected != null && item.equals(mCategorySelected)){
            holder.tvName.setTextColor(ContextCompat.getColor(context, R.color.sunsetOrange))
            holder.main.setBackgroundColor(ContextCompat.getColor(context, R.color.mineShaft))
        }else{
            holder.tvName.setTextColor(ContextCompat.getColor(context, R.color.alto))
            holder.main.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val main = view.findViewById<LinearLayout>(R.id.main)
    }
}