package com.example.android.architecture.blueprints.beetv.data.models

data class BLive(
        val id: String,
        val name: String,
        val address: String,
        val position: Int,
        val logo: String,
        val status: Boolean,
        val created_at: String,
        val updated_at: String
) {}


data class BLiveTime(
        val id: String,
        val title: String,
        val start_time: String,
        val end_time: String,
        val recording_plan: Boolean,
        val live_id: String,
        val status: Boolean,
        val created_at: String,
        val updated_at: String
){}