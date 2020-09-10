package com.example.android.architecture.blueprints.beetv.manager

import com.example.android.architecture.blueprints.beetv.data.models.BMovie

class MovieManager {

    private val mTopMovie = mutableListOf<BMovie>()
    companion object {
        var mInstance: MovieManager? = null
        fun getInstance(): MovieManager {

            if (mInstance == null) {
                mInstance = MovieManager()
            }
            return mInstance!!
        }
    }

    fun setData(movies : List<BMovie>?){
        mTopMovie.clear()
        if (movies != null)
            mTopMovie.addAll(movies)
    }

    fun getData() : MutableList<BMovie> = mTopMovie


}