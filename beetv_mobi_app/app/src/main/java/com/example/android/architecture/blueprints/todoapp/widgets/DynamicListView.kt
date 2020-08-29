package com.example.android.architecture.blueprints.todoapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.adapter.CategoryAdapter
import com.example.android.architecture.blueprints.todoapp.data.Category
import com.example.android.architecture.blueprints.todoapp.util.Constants

class DynamicListView : LinearLayout {

    private var listHashSet: LinkedHashMap<Constants.TYPE_MENU, MutableList<Category>> = linkedMapOf()
    private var recyclerHashSet: LinkedHashMap<Constants.TYPE_MENU, RecyclerView> = linkedMapOf()
    var listener: ((Constants.TYPE_MENU, String) -> Unit)? = null

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {

    }

    fun initView(type: Constants.TYPE_MENU, list: MutableList<Category>): RecyclerView {
        val adapter = CategoryAdapter(list, type, context)
        this.listHashSet.put(type, list)
        var recyclerView : RecyclerView ?= recyclerHashSet.get(type)
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        } else {
            recyclerView = RecyclerView(context)
            recyclerView.layoutManager = LinearLayoutManager(context)
            val layoutParams = LinearLayout.LayoutParams(context.resources.getDimensionPixelOffset(R.dimen.size_200), LinearLayout.LayoutParams.MATCH_PARENT)
            recyclerView.layoutParams = layoutParams
            recyclerView.adapter = adapter
            addView(recyclerView)
            recyclerHashSet.put(type, recyclerView)
        }

        adapter.mOnClickListener = { typeMenu: Constants.TYPE_MENU, s: String ->
            listener?.invoke(typeMenu, s)
        }

        return recyclerView
    }
}