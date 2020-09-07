package com.example.android.architecture.blueprints.beetv.modules.home

import androidx.lifecycle.*
import com.example.android.architecture.blueprints.beetv.Event
import com.example.android.architecture.blueprints.beetv.data.source.TasksRepository

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