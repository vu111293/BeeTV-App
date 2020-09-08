package com.example.android.architecture.blueprints.beetv.data.api

import com.example.android.architecture.blueprints.beetv.data.models.BaseResponse
import com.example.android.architecture.blueprints.beetv.data.models.LiveModel
import com.example.android.architecture.blueprints.beetv.data.models.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie")
    suspend fun getMovies(): List<Movie>

    @GET("live")
//    suspend fun getLives(): BaseResponse<LiveModel>
    suspend fun getLives(@Query("fields") fields: String): BaseResponse<LiveModel>
}