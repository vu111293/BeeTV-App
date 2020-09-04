package com.example.android.architecture.blueprints.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Category
import com.example.android.architecture.blueprints.todoapp.util.Constants

class CategoryAdapter(val category: MutableList<Category>, val type: Constants.TYPE_MENU, val context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var mOnClickListener: ((Constants.TYPE_MENU, Category) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view =
                if (type == Constants.TYPE_MENU.CHANNEL) {
                    layoutInflater.inflate(R.layout.item_channel, parent, false)
                } else if (type == Constants.TYPE_MENU.PROGRAM) {
                    layoutInflater.inflate(R.layout.item_program, parent, false)
                } else layoutInflater.inflate(R.layout.item_menu, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int = category.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = category.get(position)

        if (type == Constants.TYPE_MENU.CHANNEL) {
            holder.tvChannel.text = item.channel
            holder.tvContent.text = item.content
            holder.tvID.text = item.id
        } else   if (type == Constants.TYPE_MENU.PROGRAM){

            holder.tvTime.text = item.time
        }

        holder.tvName.text = item.name


        holder.itemView.tag = item
        holder.itemView.setOnClickListener {
            mOnClickListener?.invoke(type,item)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvID = view.findViewById<TextView>(R.id.tv_id)
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvTime = view.findViewById<TextView>(R.id.tv_time)
        val tvChannel = view.findViewById<TextView>(R.id.tv_channel)
        val tvContent = view.findViewById<TextView>(R.id.tv_content)
    }
}