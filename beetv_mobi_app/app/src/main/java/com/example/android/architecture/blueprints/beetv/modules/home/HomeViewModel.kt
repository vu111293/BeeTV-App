package com.example.android.architecture.blueprints.beetv.modules.home

import androidx.lifecycle.*
import com.example.android.architecture.blueprints.beetv.Event
import com.example.android.architecture.blueprints.beetv.data.models.Resource
import com.example.android.architecture.blueprints.beetv.data.repository.MovieRepository
import com.example.android.architecture.blueprints.beetv.data.source.TasksRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class HomeViewModel(
        private val movieRepository: MovieRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _openMenuEvent = MutableLiveData<Event<String>>()
    val openMenuEvent: LiveData<Event<String>> = _openMenuEvent
    fun openMenu(category: String) {
        _openMenuEvent.value = Event(category)
    }

    fun getTopMovie() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieRepository.getMovies()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun getLiveList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieRepository.getLives()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}