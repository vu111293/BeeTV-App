package com.example.android.architecture.blueprints.beetv.data.api

import com.example.android.architecture.blueprints.beetv.data.models.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("favorite")
    suspend fun getFavorites(@Query("fields") fields: String): BaseResponse<BFavorite>

    @POST("favorite")
    suspend fun makeMovieFavored(@Body request: BFavoriteMovieRequest): BaseResponseConfirm

    @POST("un_favorite")
    suspend fun makeMovieNoneFavored(@Body request: BFavoriteMovieRequest): BaseResponseConfirm

    @POST("favorite")
    suspend fun makeLiveFavored(@Body request: BFavoriteLiveRequest): BaseResponseConfirm

    @POST("un_favorite")
    suspend fun makeLiveNoneFavored(@Body request: BFavoriteLiveRequest): BaseResponseConfirm

    @GET("live_calendar")
    suspend fun getLiveTimeTable(@Query("fields") fields: String, @Query("filter") filter: String): BaseResponse<BLiveTime>


    // Account group
    @POST("auth/login")
    suspend fun login(@Body request: BLoginRequest): BaseResponseConfirm



    // Notice & Ads group

    @GET("carousel")
    suspend fun getAds(@Query("fields") fields: String): BaseResponse<BAds>

    @GET("notice")
    suspend fun getNotices(@Query("fields") fields: String): BaseResponse<BNotice>

}