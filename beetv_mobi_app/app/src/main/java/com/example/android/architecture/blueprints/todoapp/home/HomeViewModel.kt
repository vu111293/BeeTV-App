package com.example.android.architecture.blueprints.todoapp.home

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(
        private val tasksRepository: TasksRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {



    private val _openMenuEvent = MutableLiveData<Event<String>>()
    val openMenuEvent: LiveData<Event<String>> = _openMenuEvent
    fun openMenu(category: String) {
        _openMenuEvent.value = Event(category)
    }

}