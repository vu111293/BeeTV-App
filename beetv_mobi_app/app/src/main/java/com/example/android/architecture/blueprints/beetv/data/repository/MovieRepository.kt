package com.example.android.architecture.blueprints.beetv.data.repository

import com.example.android.architecture.blueprints.beetv.data.api.ApiHelper

class MovieRepository(private val apiHelper: ApiHelper) {
    suspend fun getMovies() = apiHelper.getMovies()
    suspend fun getLives() = apiHelper.getLives()
}