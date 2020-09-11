package com.example.android.architecture.blueprints.beetv.data.repository

import com.example.android.architecture.blueprints.beetv.data.api.ApiHelper
import com.example.android.architecture.blueprints.beetv.data.models.BMovie
import java.lang.Exception

class MediaRepository(private val apiHelper: ApiHelper) {
    suspend fun getCategories() = apiHelper.getCategories()
    suspend fun getTopMovies() = apiHelper.getTopMovies()
    suspend fun getMovies() = apiHelper.getMovies()
    suspend fun getMoviesByCategoryId(categoryId: String): List<BMovie> {
      val nestItems = apiHelper.getMoviesByCategoryId(categoryId).results.objects.rows
        return nestItems.map { it -> it.movie }
    }

    suspend fun getLives() = apiHelper.getLives()
    suspend fun getLiveTimeTable(channelId: String, startTime: Long, endTime: Long)
            = apiHelper.getLiveTimeTable(channelId, startTime, endTime)
}