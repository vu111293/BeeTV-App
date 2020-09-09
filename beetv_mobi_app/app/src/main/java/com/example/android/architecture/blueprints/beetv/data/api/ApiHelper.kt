package com.example.android.architecture.blueprints.beetv.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getMovies() = apiService.getMovies()
    suspend fun getLives() = apiService.getLives(fields = "[\"\$all\"]")
}