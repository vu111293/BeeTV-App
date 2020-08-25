package com.example.android.architecture.blueprints.todoapp.data

import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.util.Constants

data class Category(
        val type : Constants.TYPE_CATEGORY,
        val title: Int,
        val icon : Int
) {
    companion object{
        fun categoryMocks() = mutableListOf<Category>(
                Category(Constants.TYPE_CATEGORY.TV,  R.string.realtime_broadcasting,
                        R.drawable.ic_tv),
                Category(Constants.TYPE_CATEGORY.MOVIE,  R.string.movie,
                        R.drawable.ic_movie),
                Category(Constants.TYPE_CATEGORY.DRAMA,  R.string.drama,
                        R.drawable.ic_drama),
                Category(Constants.TYPE_CATEGORY.ENTERTAINMENT,  R.string.entertainment,
                        R.drawable.ic_entertainment),
                Category(Constants.TYPE_CATEGORY.EDUCATION,  R.string.preview_education,
                        R.drawable.ic_current_affairs),
                Category(Constants.TYPE_CATEGORY.CHILDRENTV,  R.string.child,
                R.drawable.ic_childrentv)


        )

        fun functionMocks(): MutableList<Category> {
            val mutableListOf = mutableListOf<Category>(
                    Category(Constants.TYPE_CATEGORY.SEARCH, R.string.search,
                            R.drawable.ic_search),
                    Category(Constants.TYPE_CATEGORY.FAVORITE, R.string.favorites,
                            R.drawable.ic_scrap),
                    Category(Constants.TYPE_CATEGORY.PLAYBACK, R.string.playback_record,
                            R.drawable.ic_scrap),
                    Category(Constants.TYPE_CATEGORY.SETTING, R.string.setting,
                            R.drawable.ic_setting)


            )
            return mutableListOf
        }
    }
}