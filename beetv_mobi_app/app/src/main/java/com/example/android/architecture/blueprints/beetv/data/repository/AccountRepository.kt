package com.example.android.architecture.blueprints.beetv.data.repository

import com.example.android.architecture.blueprints.beetv.data.api.ApiHelper

class AccountRepository(private val apiHelper: ApiHelper) {
    // Todo Logged-in is required. Please get device Id from profile
    // Please check type field <MOVIE|LIVE> to load correct GUI
    suspend fun getFavorites() = apiHelper.getFavorites("63aba510-ed3e-11ea-8956-39bdf214cd19")
}