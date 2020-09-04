package com.example.android.architecture.blueprints.todoapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.adapter.CategoryAdapter
import com.example.android.architecture.blueprints.todoapp.data.Category
import com.example.android.architecture.blueprints.todoapp.util.Constants

class DynamicListView : LinearLayout {

    private var listHashSet: LinkedHashMap<Constants.TYPE_MENU, MutableList<Category>> = linkedMapOf()
    private var recyclerHashSet: LinkedHashMap<Constants.TYPE_MENU, RecyclerView> = linkedMapOf()
    private var lineHashSet: LinkedHashMap<Constants.TYPE_MENU, View> = linkedMapOf()
    var mOnClickItemListener: ((Constants.TYPE_MENU, Category) -> Unit)? = null
    var mOnRemoveListener: ((Constants.TYPE_MENU, RecyclerView) -> Unit)? = null

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
        var recyclerView: RecyclerView? = recyclerHashSet.get(type)
        var line :View? = lineHashSet.get(type)
        val adapter = CategoryAdapter(list, type, context)
        this.listHashSet.put(type, list)
        if (recyclerView == null) {

             line = View(context)
            line.isFocusable = false
            line.layoutParams = LinearLayout.LayoutParams(context.resources.getDimensionPixelOffset(R.dimen.size_0_5), LinearLayout.LayoutParams.MATCH_PARENT)
            line.setBackgroundColor(ContextCompat.getColor(context, R.color.alto))

            recyclerView = RecyclerView(context)
            recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            val layoutParams = LinearLayout.LayoutParams(context.resources.getDimensionPixelOffset(R.dimen.size_200), LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.gravity = Gravity.CENTER
            recyclerView.layoutParams = layoutParams
            recyclerView.isFocusable = false


            recyclerHashSet.put(type, recyclerView)
            lineHashSet.put(type, line)
        }

        adapter.mOnClickListener = { typeMenu: Constants.TYPE_MENU, category: Category ->
            mOnClickItemListener?.invoke(typeMenu, category)
        }
        if (type == Constants.TYPE_MENU.CHANNEL) {
            if (recyclerHashSet.get(Constants.TYPE_MENU.PROGRAM) != null) {
//                mOnRemoveListener?.invoke(Constants.TYPE_MENU.PROGRAM, recyclerHashSet.get(Constants.TYPE_MENU.PROGRAM)!!)
                removeView(recyclerHashSet.get(Constants.TYPE_MENU.PROGRAM))
                removeView(lineHashSet.get(Constants.TYPE_MENU.PROGRAM))
            }

        }

        if (type == Constants.TYPE_MENU.PROGRAM) {

            if (recyclerHashSet.get(Constants.TYPE_MENU.CHAPTER) != null) {
//                mOnRemoveListener?.invoke(Constants.TYPE_MENU.CHAPTER, recyclerHashSet.get(Constants.TYPE_MENU.CHAPTER)!!)
                removeView(recyclerHashSet.get(Constants.TYPE_MENU.CHAPTER))
                removeView(lineHashSet.get(Constants.TYPE_MENU.CHAPTER))
            }
        }

        recyclerView.adapter = adapter
        addView(line)
        addView(recyclerView)
        return recyclerView
    }
}