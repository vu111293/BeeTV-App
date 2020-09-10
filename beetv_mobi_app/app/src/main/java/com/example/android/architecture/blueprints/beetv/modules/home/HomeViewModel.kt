package com.example.android.architecture.blueprints.beetv.modules.home

import androidx.lifecycle.*
import com.example.android.architecture.blueprints.beetv.Event
import com.example.android.architecture.blueprints.beetv.data.models.BAds
import com.example.android.architecture.blueprints.beetv.data.models.BMovie
import com.example.android.architecture.blueprints.beetv.data.models.Resource
import com.example.android.architecture.blueprints.beetv.data.repository.AccountRepository
import com.example.android.architecture.blueprints.beetv.data.repository.MediaRepository
import com.example.android.architecture.blueprints.beetv.manager.ADSManager
import com.example.android.architecture.blueprints.beetv.manager.MovieManager
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
        private val mediaRepository: MediaRepository,
        private val accountRepository: AccountRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _openMenuEvent = MutableLiveData<Event<String>>()
    val openMenuEvent: LiveData<Event<String>> = _openMenuEvent

    private val _openMovieDetailEvent = MutableLiveData<Event<String>>()
    val openMovieDetailEvent: LiveData<Event<String>> = _openMovieDetailEvent
    private val _topMovieList = MutableLiveData<MutableList<BMovie>>(MovieManager.getInstance().getData())

    val topMovieList: LiveData<MutableList<BMovie>> = _topMovieList


    fun openMenu(category: String) {
        _openMenuEvent.value = Event(category)
    }
    fun openMovieDetail(id: String) {
        _openMovieDetailEvent.value = Event(id)
    }


    fun getMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mediaRepository.getMovies()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }




    fun getLiveList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mediaRepository.getLives()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getFavoriteList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = accountRepository.getFavorites()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


}