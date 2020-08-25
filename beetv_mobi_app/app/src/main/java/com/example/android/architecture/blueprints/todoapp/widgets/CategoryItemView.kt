package com.example.android.architecture.blueprints.todoapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.util.setContentView

class CategoryItemView : LinearLayout{
    private var mName: String? = null
    private var mIcon: Int = 0
    private lateinit var mIvIcon : ImageView
    private lateinit var mTvName : TextView
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
       val view =  setContentView(R.layout.item_category)
        mIvIcon = view.findViewById(R.id.iv_icon)
        mTvName = view.findViewById(R.id.tv_name)
        loadAttrs(attrs)
        setupViews()
    }

    private fun loadAttrs(attrs: AttributeSet?) {
        if (attrs == null) return
        val types = context.obtainStyledAttributes(attrs, R.styleable.CategoryItemView)
        mName = types.getString(R.styleable.CategoryItemView_categoryName)
        mIcon = types.getResourceId(R.styleable.CategoryItemView_icon, 0)

        types.recycle()
    }

    private fun setupViews(){
        mIvIcon.setImageResource(mIcon)
        mTvName.setText(mName)
    }
}