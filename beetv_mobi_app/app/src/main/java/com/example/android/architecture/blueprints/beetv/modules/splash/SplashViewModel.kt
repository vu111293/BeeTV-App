package com.example.android.architecture.blueprints.beetv.modules.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.android.architecture.blueprints.beetv.data.models.Resource
import com.example.android.architecture.blueprints.beetv.data.repository.AccountRepository
import com.example.android.architecture.blueprints.beetv.data.repository.MediaRepository
import kotlinx.coroutines.Dispatchers

class SplashViewModel(
        private val mediaRepository: MediaRepository,
        private val accountRepository: AccountRepository
) : ViewModel() {

    fun getCategories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mediaRepository.getCategories()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getTopMovie() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mediaRepository.getTopMovies()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getAdsList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = accountRepository.getAds()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getNoticeList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = accountRepository.getNotices()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}