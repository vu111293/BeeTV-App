package com.example.android.architecture.blueprints.beetv.data.repository

import com.example.android.architecture.blueprints.beetv.data.api.ApiHelper

class MediaRepository(private val apiHelper: ApiHelper) {
    suspend fun getCategories() = apiHelper.getCategories()
    suspend fun getTopMovies() = apiHelper.getTopMovies()
    suspend fun getMovies() = apiHelper.getMovies()
    suspend fun getLives() = apiHelper.getLives()
}