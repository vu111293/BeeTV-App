package com.example.android.architecture.blueprints.todoapp.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.data.Category
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

class MenuViewModel(
        private val tasksRepository: TasksRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _openMovieDetailEvent = MutableLiveData<Event<String>>()
    val openMovieDetailEvent: LiveData<Event<String>> = _openMovieDetailEvent
    fun openMovieDetail(movieID: String) {
        _openMovieDetailEvent.value = Event(movieID)
    }
}