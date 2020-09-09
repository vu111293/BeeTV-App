package com.example.android.architecture.blueprints.beetv.data.models

data class BAccount(
        val id: String,
        val username: String,
        val fullname: String,
        val birthday: String,
        val user_type: String,
        val email: String,
        val mac: String,
        val batch_id: String,
        val node_id: String,
        val logical_id: String,
        val created_at_unix_timestamp: String,
        val status: Boolean,
        val created_at: String,
        val updated_at: String
) {
}

data class BLoginRequest(
        val username: String,
        val password: String
) {}