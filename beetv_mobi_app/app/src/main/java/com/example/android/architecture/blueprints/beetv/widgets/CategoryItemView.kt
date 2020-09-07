package com.example.android.architecture.blueprints.beetv.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.util.setContentView

class CategoryItemView : ConstraintLayout{
    private var mName: String? = null
    private var mIcon: Int = 0
    private lateinit var mIvIcon : ImageView
    private lateinit var mTvName : TextView
    private lateinit var mMain : ConstraintLayout
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
        mMain = view.findViewById(R.id.main)
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

    public fun setColor(resId : Int){

        mMain.setBackgroundColor(ContextCompat.getColor(context, resId))

        if (resId == R.color.mineShaft){
            mIvIcon.setColorFilter(ContextCompat.getColor(context, R.color.alto), android.graphics.PorterDuff.Mode.MULTIPLY)
            mTvName.setTextColor(ContextCompat.getColor(context,  R.color.alto))
        }else{
            mIvIcon.setColorFilter(ContextCompat.getColor(context,  R.color.mineShaft), android.graphics.PorterDuff.Mode.MULTIPLY)
            mTvName.setTextColor(ContextCompat.getColor(context,  R.color.mineShaft))
        }
    }
}