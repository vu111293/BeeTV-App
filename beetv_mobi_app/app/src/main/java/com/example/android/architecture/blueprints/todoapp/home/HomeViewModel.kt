package com.example.android.architecture.blueprints.todoapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

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