package com.example.android.architecture.blueprints.beetv.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getCategories() = apiService.getCategoryMedia(fields = "fields=[\"\$all\"]")
    suspend fun getTopMovies() = apiService.getTopMovies(fields = "[\"\$all\"]")
    suspend fun getMovies() = apiService.getMovies()
    suspend fun getLives() = apiService.getLives(fields = "[\"\$all\"]")

}