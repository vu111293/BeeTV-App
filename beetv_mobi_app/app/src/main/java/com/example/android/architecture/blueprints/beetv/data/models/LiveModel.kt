package com.example.android.architecture.blueprints.beetv.data.models

data class LiveModel(
        var id: String,
        var name: String,
        var address: String,
        var position: Int,
        var logo: String,
        var status: Boolean,
        var created_at: String,
        var updated_at: String
) {}