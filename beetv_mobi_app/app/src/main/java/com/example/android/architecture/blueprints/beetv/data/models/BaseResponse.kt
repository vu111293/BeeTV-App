package com.example.android.architecture.blueprints.beetv.data.models

import java.util.*

data class BaseResponse<T>(
        val code: String,
        val results: ResultsResponse<T>,
        val pagination: PagingResponse
) {}

data class ResultsResponse<T>(
    val objects: ObjectsResponse<T>
){}

data class ObjectsResponse<T>(
   val count: Int,
   val rows: List<T>
) {}

data class PagingResponse(
        val current_page: Int,
        val next_page: Int,
        val prev_page: Int,
        val limit: Int
) {}