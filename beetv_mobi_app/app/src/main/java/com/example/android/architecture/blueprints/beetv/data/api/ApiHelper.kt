package com.example.android.architecture.blueprints.beetv.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getCategories() = apiService.getCategoryMedia(fields = "fields=[\"\$all\"]")
    suspend fun getTopMovies() = apiService.getTopMovies(fields = "[\"\$all\"]")
    suspend fun getMovies() = apiService.getMovies()
    suspend fun getMoviesByCategoryId(categoryId: String) = apiService.getMoviesByCategoryId(
            fields = "[\"\$all\", {\"movie\": [\"\$all\"]} , {\"category\": [\"\$all\"] }]",
            filter = "{\"category_id\": {\"\$in\": [\"${categoryId}\"]}}"
    )
    suspend fun getLives() = apiService.getLives(fields = "[\"\$all\"]")
    suspend fun getFavorites(deviceId: String)
            = apiService.getLives(fields = "ields=[\"\$all\", {\"movie\": [\"\$all\"] }, {\"live\": [\"\$all\"]} ]&filter={\"device_id\": \"${deviceId}\"}")
    suspend fun getLiveTimeTable(channelId: String, startTime: Long, endTime: Long) = apiService.getLiveTimeTable(
            fields = "[\"\$all\"]",
            filter = "{\"start_time\":{\"\$between\": [${startTime}, ${endTime}]}}"
    )
    suspend fun getAds() = apiService.getAds(fields = "[\"\$all\"]")
    suspend fun getNotices() = apiService.getNotices(fields = "[\"\$all\"]")




}