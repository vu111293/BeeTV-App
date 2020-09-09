package com.example.android.architecture.blueprints.beetv.data.models

import java.util.*

data class BaseResponseConfirm(
        val code: String,
        val type: String,
        val message: String, // this is error message
        val results: Any
){}

data class BaseResponse<T>(
        val code: String,
        val type: String,
        val message: String,
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