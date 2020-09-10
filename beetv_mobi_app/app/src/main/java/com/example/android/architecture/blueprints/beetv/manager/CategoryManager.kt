package com.example.android.architecture.blueprints.beetv.manager

import com.example.android.architecture.blueprints.beetv.data.models.BCategory

class CategoryManager {

    private val mCategories = mutableListOf<BCategory>()
    companion object {
        var mInstance: CategoryManager? = null
        fun getInstance(): CategoryManager {

            if (mInstance == null) {
                mInstance = CategoryManager()
            }
            return mInstance!!
        }
    }

    fun setData(categories : List<BCategory>?){
        mCategories.clear()
        if (categories != null)
        mCategories.addAll(categories)
    }

    fun getData() : MutableList<BCategory> = mCategories


}