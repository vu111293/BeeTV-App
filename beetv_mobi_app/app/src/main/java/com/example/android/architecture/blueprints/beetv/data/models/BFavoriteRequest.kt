package com.example.android.architecture.blueprints.beetv.data.models

data class BFavoriteMovieRequest(
        val device_id: String,
        val movie_id: String
) {}

data class BFavoriteLiveRequest(
        val device_id: String,
        val live_id: String
) {}