package com.example.android.architecture.blueprints.beetv.data.api

import com.example.android.architecture.blueprints.beetv.data.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Media group

    @GET("category")
    suspend fun getCategoryMedia(@Query("fields") fields: String): BaseResponse<BCategory>

    @GET("movie/get_top_10")
    suspend fun getTopMovies(@Query("fields") fields: String): BaseResponse<BMovie>

    @GET("movie")
    suspend fun getMovies(): BaseResponse<BMovie>

    @GET("live")
    suspend fun getLives(@Query("fields") fields: String): BaseResponse<LiveModel>


    // Account group



    // Notice & Ads group


}